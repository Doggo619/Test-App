package com.base.unscramblewords.utils;

import static com.base.unscramblewords.utils.Constant.DEVELOPMENT_MODE;
import static com.base.unscramblewords.utils.Constant.DEBUG_TAG;
import static com.base.unscramblewords.utils.Constant.TYPE_WIFI;
import static com.base.unscramblewords.utils.Constant.TYPE_MOBILE;
import static com.base.unscramblewords.utils.Constant.TYPE_NOT_CONNECTED;


import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RawRes;
import androidx.appcompat.app.AlertDialog;

import com.base.unscramblewords.R;
import com.google.android.material.textfield.TextInputEditText;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Common {

    public static boolean isObjectNotNullOrEmpty(Object object) {
        if (null != object) {
            return (!object.toString().equals(""));
        } else {
            return false;
        }
    }

    public static void putErrorLog(String message) {
        if (DEVELOPMENT_MODE) {
            Log.e(DEBUG_TAG, message);
        }
    }
    public static void putDebugLog(String message) {
        if (DEVELOPMENT_MODE) {
            Log.d(DEBUG_TAG, message);
        }
    }

    public static int getConnectivityStatus(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;

        }
        return TYPE_NOT_CONNECTED;
    }
    public static void showToast(Context context, String message) {
        try {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        } catch (WindowManager.BadTokenException exception) {
//            FirebaseCrashlytics.getInstance().recordException(exception);
        }

    }



    public static void showAlertForChangeDate(final Activity mActivity) {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                Objects.requireNonNull(mActivity), R.style.CustomDialogTheme);
        alertDialogBuilder.setTitle(mActivity.getString(R.string.alert_title_date_modified));
        alertDialogBuilder.setMessage(mActivity.getString(R.string.alert_msg_date_modified))
                .setPositiveButton(mActivity.getString(R.string.action_make_date_time_proper), null);

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(final DialogInterface dialog) {
                Button btnPositive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                btnPositive.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        mActivity.startActivityForResult(new Intent(android.provider.Settings.ACTION_DATE_SETTINGS), 26);
                        alertDialog.dismiss();
                    }
                });
            }
        });
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }


    public static boolean isGivenStringNotNullAndNotEmpty(String str) {
        return str != null && !str.isEmpty() && !str.trim().isEmpty();
    }


    public static double calculateScorePercentage(int correctAnswers, int totalQuestions) {
        return correctAnswers * 100.0 / totalQuestions;
    }

    public static double calculateCompletionPercentage(int completedItems, int totalItems) {
        return completedItems * 100.0 / totalItems;
    }

    public static String[] splitGivenStr(String userAnswer, String answerDelimiterStr) {
        return userAnswer.split(answerDelimiterStr);
    }


    public static void shareContent(String packageName, String content, String title, Context context) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, content);
        if (Common.isObjectNotNullOrEmpty(packageName)) {
            shareIntent.setPackage(packageName);
        }
        context.startActivity(Intent.createChooser(shareIntent, (isObjectNotNullOrEmpty(title)) ? title : content));
    }

    public static String joinGivenStrings(String delimiter, String... givenStringArray) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < givenStringArray.length; i++) {
            result.append(givenStringArray[i]);
            if (i < givenStringArray.length - 1)
                result.append(delimiter);
        }
        return result.toString();
    }

    public static void editBoxTextFilter(CharSequence inputStr, EditText parentNameEditText, String allowedTextRegex) {
        String newStr = inputStr.toString();
        newStr = newStr.replaceAll(allowedTextRegex, "");
        if (!inputStr.toString().equals(newStr)) {
            parentNameEditText.setText(newStr);
            parentNameEditText.setSelection(newStr.length());
        }
    }

    public static String getSecondsInTimerFormat(long remainingMilliseconds) {
        return String.format(Locale.US, "%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(remainingMilliseconds),
                TimeUnit.MILLISECONDS.toMinutes(remainingMilliseconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(remainingMilliseconds)),
                TimeUnit.MILLISECONDS.toSeconds(remainingMilliseconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(remainingMilliseconds)));
    }

    public static boolean isGivenStringNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isEmailValid(String str) {
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(str).matches();
    }

    public static void hideKeyboard(View view, Context context) {
        InputMethodManager inputManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public static long getCurrentTimeStampInSecs() {
        return System.currentTimeMillis() / 1000;
    }

    public static long getCurrentDateInSecs() {
        long epochTime = Common.getCurrentTimeStampInSecs();
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat crunchifyFormat = new SimpleDateFormat("MMM dd yyyy");
        String currentTime = crunchifyFormat.format(today);
        try {
            Date date = crunchifyFormat.parse(currentTime);
            epochTime = date.getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return epochTime;
    }

    public static void gotoPlayStoreLink(Context context) {
        if (context != null) {
            try {
//                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + context.getPackageName())));
                // todo: added below line for testing
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.ahaguru.quiz")));
            } catch (ActivityNotFoundException e) {
//                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + context.getPackageName())));
                // todo: added below line for testing
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.ahaguru.quiz")));
            }
        }
    }

    public static void openBrowserIntent(Context context, String link) {
        context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link)));
    }

    public static String readRawResource(@RawRes int res, Context applicationContext) {
        return readStream(applicationContext.getResources().openRawResource(res));
    }

    private static String readStream(InputStream is) {
        Scanner s = new Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

    public static Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }

    public static List<Integer> getQuestionIDs(int totalSlides, int noOfSlidesToRender, int setId) {
        if (setId > totalSlides) { // this is to generate infinite sets
            setId = setId % totalSlides;
        }
        List<Integer> result = new ArrayList<>();
        int startWith = 1;

        int possibleSetCount = totalSlides / noOfSlidesToRender; // Total unique possible sets for given questions and max questions in set
        possibleSetCount = (possibleSetCount <= 0) ? 1 : possibleSetCount;
        // First possible uniques sets should be running number
        if (setId <= possibleSetCount) {
            startWith = noOfSlidesToRender * (setId - 1);
        } else if (setId % possibleSetCount == 1) { // Starting question of the First set of every unique set will be the same as the set number
            startWith = setId - 1;
        } else { // logic to handle other sets
            int multiplier = (setId % possibleSetCount) - 1;
            if (multiplier == -1) {
                multiplier = possibleSetCount - 1;
            }

            startWith = (setId - multiplier) + (noOfSlidesToRender * multiplier) - 1;
            if (startWith > totalSlides) {
                startWith -= totalSlides;
            }
        }


        // to reset the counter when max question count is reached
        int q = 0;
        int restart = 0;
        boolean flag = false;
        for (int i = 1; i <= noOfSlidesToRender; i++) {
            q = startWith + i;
            if (q <= totalSlides)
                result.add(q);
            else {
                result.add(restart + 1);
                restart++;
            }
        }
        return result;
    }

    private static void setEditTextProperties(Context context, TextView editText) {
        editText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        editText.setMaxLines(1);
        editText.setTextColor(context.getResources().getColor(R.color.black));
        editText.setTextSize(18);
    }

    public static void setEditTextInputType(TextInputEditText editText, int inputType, int maxLength) {
        editText.setInputType(inputType);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
    }

    public static double calculateCompletionPercentage(long currentPosition, long duration) {
        return currentPosition * 100.0 / duration;
    }

    public static String extractYoutubeVideoId(String ytUrl) {

        String vId = null;

        String pattern = "(?<=watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(ytUrl);

        if(matcher.find()){
            vId= matcher.group();
        }
        return vId;
    }
    public static boolean isInternetConnected(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
