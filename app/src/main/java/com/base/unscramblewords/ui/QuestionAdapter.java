package com.base.unscramblewords.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.base.unscramblewords.databinding.FragmentQuestionBinding;
import com.base.unscramblewords.entity.quizEntity.Questions;

import org.jetbrains.annotations.NotNull;


public class QuestionAdapter extends ListAdapter<Questions, QuestionAdapter.ViewHolder> {

    Context context;
    private final OnClickListener onClickListener;
    private final ViewPager2 viewPager;
    int totalQuestions = 0;
    String isCorrect;


    private static final DiffUtil.ItemCallback<Questions> QUESTIONS_ITEM_CALLBACK = new DiffUtil.ItemCallback<Questions>() {
        @Override
        public boolean areItemsTheSame(@NonNull @NotNull Questions oldItem, @NonNull @NotNull Questions newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull @NotNull Questions oldItem, @NonNull @NotNull Questions newItem) {
            return oldItem.equals(newItem);
        }
    };

    public QuestionAdapter(Context context, OnClickListener onClickListener, ViewPager2 viewPager) {
        super(QUESTIONS_ITEM_CALLBACK);
        this.context = context;
        this.onClickListener = onClickListener;
        this.viewPager = viewPager;
    }

    @NonNull
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentQuestionBinding binding = FragmentQuestionBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.ViewHolder holder, int position) {
        Questions questions = getItem(position);

        if (questions != null) {

            totalQuestions = getItemCount();

            holder.binding.tvQuestion.setText(questions.getQuestion());
            holder.binding.rbOption1.setText(questions.getOption1());
            holder.binding.rbOption2.setText(questions.getOption2());
            holder.binding.rbOption3.setText(questions.getOption3());
            holder.binding.rbOption4.setText(questions.getOption4());

            holder.binding.btnSave.setOnClickListener(v -> {
                String givenAnswer = fetchSelectedAnswer(holder.binding.radioGroup);
                questions.setGivenAnswer(givenAnswer);

                if (givenAnswer.equals(questions.getCorrectAnswer())) {
                    isCorrect = "Correct";
                } else if (givenAnswer == null || givenAnswer.isEmpty()) {
                    isCorrect = "UnAnswered";
                }
                else if (!givenAnswer.equals(questions.getCorrectAnswer())) {
                    isCorrect = "InCorrect";
                }

                if (onClickListener != null) {
                    onClickListener.onClick(questions.getQuestionId(), isCorrect, totalQuestions);
                }
                if (viewPager != null) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                }
            });
        }
    }


    private String fetchSelectedAnswer(RadioGroup radioGroup) {
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId != -1) {
            View selectedRadioButton = radioGroup.findViewById(selectedRadioButtonId);

            if (selectedRadioButton instanceof RadioButton) {
                return ((RadioButton) selectedRadioButton).getText().toString();
            }
        }
        return "";
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        FragmentQuestionBinding binding;

        public ViewHolder(@NonNull FragmentQuestionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnClickListener {
        void onClick(int questionId, String isCorrect, int totalQuestions);
    }
}