package com.base.unscramblewords.utils;

public class Constant {

    public static final String SKILL_CHART_DATABASE_NAME = "skillchartapp_db";
    public static final boolean DEVELOPMENT_MODE = true;
    public static final String DEBUG_TAG = "SkillChartApp";
    public static final String AHA_TOKEN = "sW5hoVkoub7xmBHAXrzQBr1P3UWFOkK88cXZzH4ZHx8%3D";
    public static final String TOKEN = "%2FII0I%2B8pklFW%2BZzT7ApZCRzSU8ubKdZD%2FI3uBTNQ%2BdA%3D";
    public static final String BEARER_TOKEN_PREFIX = "Bearer ";
    public static final String QUESTION = "Questions";
    public static final String SKILLS = "Skills";
    public static final String TESTS = "Tests";
    public static final String BADGE = "Badges";
    public static final String STARS = "Stars";
    public static final String CERTIFICATES = "Certificates";


    // API Response Codes
    public static final int STATUS_CODE_SUCCESS = 200;
    public static final int STATUS_CODE_SERVER_RESPONSE_MISSING_DATA = 400;
    public static final int STATUS_CODE_TOKEN_MISMATCH = 401;
    public static final int STATUS_CODE_CONNECTIVITY_ISSUE = 504;
    public static final int STATUS_CODE_TIMEOUT = 408;
    public static final int DATA_NOT_FOUND = 404;
    public static final int STATUS_CODE_RESPONSE_NOT_FOUND = 404;
    public static final int STATUS_ERROR_CODE_CONFLICT = 409;
    public static final int STATUS_CODE_INVALID_USER = 412;


    public static String WEBINAPP_ERROR_NO_NETWORK = "You seem to be offline. Please connect to network to continue.";
    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;

    public static String SKILLCHARTJSON = "{\n" +
            "\"status\":200,\n" +
            "\"message\": \"Success\",\n" +
            "\"last_updated\":1234567890,\n" +
            "\"data\":{\n" +
            "\"overall_stats\":{\n" +
            "\"questions_attempted\":250,\n" +
            "\"skills_completed\":137,\n" +
            "\"tests_completed\":250,\n" +
            "\"badges_count \":60,\n" +
            "\"stars_earned\":13,\n" +
            "\"certificates_count\":20\n" +
            "},\n" +
            "\"skillcharts\":[\n" +
            "{\n" +
            "\"skillchart_id\":1,\n" +
            "\"name\": \"Skillchart for IIT JEE Physics\",\n" +
            "\"is_deleted\":0,\n" +
            "\"display_order\":1\n" +
            "},\n" +
            "{\n" +
            "\"skillchart_id\":2,\n" +
            "\"name\": \"Skillchart for IIT JEE Maths\",\n" +
            "\"is_deleted\":0,\n" +
            "\"display_order\":2\n" +
            "}\n" +
            "],\n" +
            "\"user_skillchart_stat\":[\n" +
            "{\n" +
            "\"skillchart_id\":1,\n" +
            "\"total_skills\":295,\n" +
            "\"skills_acquired\":137\n" +
            "},\n" +
            "{\n" +
            "\"skillchart_id\":2,\n" +
            "\"total_skills\":235,\n" +
            "\"skills_acquired\":87\n" +
            "}\n" +
            "]\n" +
            "}\n" +
            "}\n";

    public static final String SKILLCHARTPROGRESSJSON = "{\n" +
            "\"status\":200,\n" +
            "\"message\": \"Success\",\n" +
            "\"data\":{\n" +
            "\"stats\":{\n" +
            "\"questions_attempted\":120,\n" +
            "\"skills_completed\":117,\n" +
            "\"tests_completed\":145,\n" +
            "\"badges_count \":12,\n" +
            "\"stars_earned\":11,\n" +
            "\"certificates_count\":10\n" +
            "}\n" +
            "}\n" +
            "}\n";
    public static final String SKILLCHARTSECTIONDETAILSJSON = "{\n" +
            "\"status\":200,\n" +
            "\"message\": \"Success\",\n" +
            "\"last_updated\":1234567890,\n" +
            "\"data\":{\n" +
            "\"sections\":[\n" +
            "{\n" +
            "\"section_id\":1,\n" +
            "\"name\": \"Mechanics\",\n" +
            "\"is_deleted\":0,\n" +
            "\"display_order\":1,\n" +
            "\"topics\":[\n" +
            "{\n" +
            "\"topic_id\":1,\n" +
            "\"name\": \"Friction\",\n" +
            "\"is_deleted\":0,\n" +
            "\"display_order\":1,\n" +
            "\"skills\":[\n" +
            "{\n" +
            "\"skill_id\":1,\n" +
            "\"name\":\"Archimedes Principle\",\n" +
            "\"is_deleted\":0,\n" +
            "\"display_order\":1\n" +
            "},\n" +
            "{\n" +
            "\"skill_id\":2,\n" +
            "\"name\": \"Electromagnetic Induction\",\n" +
            "\"is_deleted\":0,\n" +
            "\"display_order\":2\n" +
            "}\n" +
            "]\n" +
            "},\n" +
            "{\n" +
            "\"topic_id\":2,\n" +
            "\"name\": \"Projectile Motion\",\n" +
            "\"is_deleted\":0,\n" +
            "\"display_order\":2,\n" +
            "\"skills\":[\n" +
            "{\n" +
            "\"skill_id\":1,\n" +
            "\"name\":\"Archimedes Principle\",\n" +
            "\"is_deleted\":0,\n" +
            "\"display_order\":1\n" +
            "},\n" +
            "{\n" +
            "\"skill_id\":2,\n" +
            "\"name\": \"Electromagnetic Induction\",\n" +
            "\"is_deleted\":0,\n" +
            "\"display_order\":2\n" +
            "}\n" +
            "]\n" +
            "}\n" +
            "]\n" +
            "},\n" +
            "{\n" +
            "\"section_id\":2,\n" +
            "\"name\": \"Electric Charges and Fields\",\n" +
            "\"is_deleted\":0,\n" +
            "\"display_order\":2,\n" +
            "\"topics\":[\n" +
            "{\n" +
            "\"topic_id\":3,\n" +
            "\"name\": \"Resolution of forces\",\n" +
            "\"is_deleted\":0,\n" +
            "\"display_order\":1,\n" +
            "\"skills\":[\n" +
            "{\n" +
            "\"skill_id\":3,\n" +
            "\"name\":\"Archimedes Principle\",\n" +
            "\"is_deleted\":0,\n" +
            "\"display_order\":1\n" +
            "},\n" +
            "{\n" +
            "\"skill_id\":4,\n" +
            "\"name\": \"Electromagnetic Induction\",\n" +
            "\"is_deleted\":0,\n" +
            "\"display_order\":2\n" +
            "}\n" +
            "]\n" +
            "},\n" +
            "{\n" +
            "\"topic_id\":4,\n" +
            "\"name\": \"Energy\",\n" +
            "\"is_deleted\":0,\n" +
            "\"display_order\":2,\n" +
            "\"skills\":[\n" +
            "{\n" +
            "\"skill_id\":3,\n" +
            "\"name\":\"Archimedes Principle\",\n" +
            "\"is_deleted\":0,\n" +
            "\"display_order\":1\n" +
            "},\n" +
            "{\n" +
            "\"skill_id\":4,\n" +
            "\"name\": \"Electromagnetic Induction\",\n" +
            "\"is_deleted\":0,\n" +
            "\"display_order\":2\n" +
            "}\n" +
            "]\n" +
            "}\n" +
            "]\n" +
            "}\n" +
            "]\n" +
            "}\n" +
            "}\n";
    public static final String SKILLCHARTSKILLSUSERSECTIONSTAT = "{\n" +
            "\"status\":200,\n" +
            "\"message\": \"Success\",\n" +
            "\"data\":{\n" +
            "\"user_section_stat\":[\n" +
            "{\n" +
            "\"section_id\":1,\n" +
            "\"total_skills\":295,\n" +
            "\"skills_acquired\":137,\n" +
            "\"expert_level_completion\":30,\n" +
            "\"stars_count\":3,\n" +
            "\"user_topic_stat\":[\n" +
            "{\n" +
            "\"topic_id\":1,\n" +
            "\"stars_count\":2,\n" +
            "\"skill_matrix\":[\n" +
            "{\n" +
            "\"skill_id\":1,\n" +
            "\"is_earned_star\":0,\n" +
            "\"skill_level\":1\n" +
            "},\n" +
            "{\n" +
            "\"skill_id\":2,\n" +
            "\"is_earned_star\":0,\n" +
            "\"skill_level\":0\n" +
            "},\n" +
            "{\n" +
            "\"skill_id\":3,\n" +
            "\"is_earned_star\":1,\n" +
            "\"skill_level\":2\n" +
            "},\n" +
            "{\n" +
            "\"skill_id\":4,\n" +
            "\"is_earned_star\":0,\n" +
            "\"skill_level\":0\n" +
            "},\n" +
            "{\n" +
            "\"skill_id\":5,\n" +
            "\"is_earned_star\":0,\n" +
            "\"skill_level\":3\n" +
            "},\n" +
            "{\n" +
            "\"skill_id\":6,\n" +
            "\"is_earned_star\":0,\n" +
            "\"skill_level\":2\n" +
            "}\n" +
            "]\n" +
            "},\n" +
            "{\n" +
            "\"topic_id\":2,\n" +
            "\"stars_count\":1,\n" +
            "\"skill_matrix\":[\n" +
            "{\n" +
            "\"skill_id\":1,\n" +
            "\"is_earned_star\":1,\n" +
            "\"skill_level\":2\n" +
            "},\n" +
            "{\n" +
            "\"skill_id\":2,\n" +
            "\"is_earned_star\":0,\n" +
            "\"skill_level\":0\n" +
            "},\n" +
            "{\n" +
            "\"skill_id\":3,\n" +
            "\"is_earned_star\":0,\n" +
            "\"skill_level\":2\n" +
            "},\n" +
            "{\n" +
            "\"skill_id\":4,\n" +
            "\"is_earned_star\":0,\n" +
            "\"skill_level\":0\n" +
            "},\n" +
            "{\n" +
            "\"skill_id\":5,\n" +
            "\"is_earned_star\":0,\n" +
            "\"skill_level\":3\n" +
            "},\n" +
            "{\n" +
            "\"skill_id\":6,\n" +
            "\"is_earned_star\":0,\n" +
            "\"skill_level\":2\n" +
            "}\n" +
            "]\n" +
            "}\n" +
            "]\n" +
            "},\n" +
            "{\n" +
            "\"section_id\":2,\n" +
            "\"total_skills\":235,\n" +
            "\"skills_acquired\":87,\n" +
            "\"expert_level_completion\":46,\n" +
            "\"stars_count\":1,\n" +
            "\"user_topic_stat\":[\n" +
            "{\n" +
            "\"topic_id\":1,\n" +
            "\"stars_count\":1,\n" +
            "\"skill_matrix\":[\n" +
            "{\n" +
            "\"skill_id\":1,\n" +
            "\"is_earned_star\":0,\n" +
            "\"skill_level\":1\n" +
            "},\n" +
            "{\n" +
            "\"skill_id\":2,\n" +
            "\"is_earned_star\":0,\n" +
            "\"skill_level\":0\n" +
            "},\n" +
            "{\n" +
            "\"skill_id\":3,\n" +
            "\"is_earned_star\":0,\n" +
            "\"skill_level\":2\n" +
            "},\n" +
            "{\n" +
            "\"skill_id\":4,\n" +
            "\"is_earned_star\":0,\n" +
            "\"skill_level\":0\n" +
            "},\n" +
            "{\n" +
            "\"skill_id\":5,\n" +
            "\"is_earned_star\":1,\n" +
            "\"skill_level\":3\n" +
            "},\n" +
            "{\n" +
            "\"skill_id\":6,\n" +
            "\"is_earned_star\":1,\n" +
            "\"skill_level\":2\n" +
            "}\n" +
            "]\n" +
            "},\n" +
            "{\n" +
            "\"topic_id\":2,\n" +
            "\"stars_count\":1,\n" +
            "\"skill_matrix\":[\n" +
            "{\n" +
            "\"skill_id\":1,\n" +
            "\"is_earned_star\":0,\n" +
            "\"skill_level\":1\n" +
            "},\n" +
            "{\n" +
            "\"skill_id\":2,\n" +
            "\"is_earned_star\":0,\n" +
            "\"skill_level\":0\n" +
            "},\n" +
            "{\n" +
            "\"skill_id\":3,\n" +
            "\"is_earned_star\":0,\n" +
            "\"skill_level\":2\n" +
            "},\n" +
            "{\n" +
            "\"skill_id\":4,\n" +
            "\"is_earned_star\":0,\n" +
            "\"skill_level\":0\n" +
            "},\n" +
            "{\n" +
            "\"skill_id\":5,\n" +
            "\"is_earned_star\":1,\n" +
            "\"skill_level\":3\n" +
            "},\n" +
            "{\n" +
            "\"skill_id\":6,\n" +
            "\"is_earned_star\":0,\n" +
            "\"skill_level\":2\n" +
            "}\n" +
            "]\n" +
            "}\n" +
            "]\n" +
            "}\n" +
            "]\n" +
            "}\n" +
            "}";
}
