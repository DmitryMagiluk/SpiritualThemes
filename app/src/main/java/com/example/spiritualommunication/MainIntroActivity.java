package com.example.spiritualommunication;

import android.os.Bundle;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

public class MainIntroActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setFullscreen(true);
        super.onCreate(savedInstanceState);

        setButtonBackVisible(true);
        setButtonNextVisible(true);
        setButtonCtaVisible(false);

        setButtonNextFunction(BUTTON_NEXT_FUNCTION_NEXT_FINISH);
        setButtonBackFunction(BUTTON_BACK_FUNCTION_BACK);

        setPageScrollDuration(800);


        addSlide(new SimpleSlide.Builder()
                .title("Приветствую вас!")
                .description("Данное служение ДУХОВНЫЕ ТЕМЫ ДЛЯ ОБЩЕНИЯ появилось из нужды в простом духовном христианском общении, духовной поддержке и ученичестве.")
                //.image(R.drawable.ic_baseline_accessibility_new_24)
                .background(R.color.introActivity6)
                .backgroundDark(R.color.introActivityButton)
                .scrollable(true)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("Цели ДУХОВНЫХ ТЕМ ДЛЯ ОБЩЕНИЯ:")
                .description("- духовная поддержка и попечение (как новообращённых, так и зрелых христиан);\n" +
                        "- духовный рост христиан, как подопечного, так и ведущего (через служение ближнему),\n" +
                        "- преображение всех сфер жизни в угодные Богу;\n" +
                        "- простосердечное братское общение со Словом Божиим,\n" +
                        "- ДРУЖБА И ОТНОШЕНИЯ на равных, а не быть экспертом, профессионалом, наставником или учителем (\"А вы не называйтесь учителями, ибо один у вас Учитель - Христос, все же вы - братья;\" (Мф.23:8);\n" +
                        "- Практическое применение пройденных духовных тем;\n" +
                        "- ученичество (кроме вышеперечисленного, развивать и мотивировать и его на служение другим)")
                //.image(R.drawable.ic_baseline_accessibility_new_24)
                .background(R.color.introActivity6)
                .backgroundDark(R.color.introActivityButton)
                .scrollable(true)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("Духовные темы состоят из мест Библии и вопросов, чтобы:")
                .description("- размышляя над Словом Божиим, обратится к СЕРДЦУ, а не знаниям человека,\n" +
                        "- направить на то, как воспользоваться этим в своей жизни.")
                //.image(R.drawable.ic_baseline_accessibility_new_24)
                .background(R.color.introActivity6)
                .backgroundDark(R.color.introActivityButton)
                .scrollable(true)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("ВНАЧАЛЕ")
                .description("Помните, что в духовном общении с вами присутствует Господь. \"Ибо, где двое или трое собраны во имя Мое, там Я посреди них.\" (Мф.18:20).\n" +
                        "Выберите одну из тем, прочитайте первый стих внутри темы и затем по очереди отвечайте на следующие за стихом вопросы. Далее переходите к следующему стиху и вопросам.\n\n" +
                        "В конце каждой темы предложен стих для запоминания, чтобы тема и общение остались в памяти.\n" +
                        "Рекомендуется заканчивать общение молитвой \uD83D\uDE4F\uD83C\uDFFD")
                //.image(R.drawable.ic_baseline_accessibility_new_24)
                .background(R.color.introActivity6)
                .backgroundDark(R.color.introActivityButton)
                .scrollable(true)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("")
                .description("При общении по ДУХОВНЫМ ТЕМАМ не обязательно: \n" +
                        "- проходить всю тему за 1 раз;\n" +
                        "- задавать все указанные вопросы.\n" +
                        "т.к. это не семинар, не лекция, не шаблон или закон.\n\n" +
                        "Вы можете \uD83D\uDE0A:\n" +
                        "- использовать и другие места Библии, подходящие к теме;\n" +
                        "- задавать ваши вопросы, которые будут наиболее соответствующими к вашему диалогу;\n" +
                        "- делится тем, что происходило в вашей жизни.\n" +
                        "Одним словом, вести общение так, как ведёт Дух Святой.\n" +
                        "А духовные темы в помощь вам.")
                //.image(R.drawable.ic_baseline_accessibility_new_24)
                .background(R.color.introActivity6)
                .backgroundDark(R.color.introActivityButton)
                .scrollable(true)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("На вопросы рекомендуется отвечать обоим, чтобы:")
                .description("- развивать дружеские и доверительные отношения в форме ДИАЛОГА (а не монолога),\n" +
                        "- устранить построение барьеров в общении,\n" +
                        "- предотвратить превозношение (учитель-ученик, начальник-подчиненный). \"Больший из вас да будет вам слуга:\" (Мф.23:11).\n" +
                        "\n" +
                        "Общение можно проводить как при личной встрече, так и по телефону.")
                //.image(R.drawable.ic_baseline_accessibility_new_24)
                .background(R.color.introActivity6)
                .backgroundDark(R.color.introActivityButton)
                .scrollable(true)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("Приглашаю и вас стать ДУХОВНЫМ ДРУГОМ для ближнего!\n")
                .description("Благословений вам! Стихи в пожелание вам на все общения \uD83D\uDE07\uD83D\uDE4F\uD83C\uDFFD:\n" +
                        "\n" +
                        "\"Ибо, где двое или трое собраны во имя Мое, там Я посреди них.\" (Мф.18:20)\n" +
                        "\n" +
                        "\"А вы не называйтесь учителями, ибо один у вас Учитель - Христос, все же вы - братья;\" (Мф.23:8)\n" +
                        "\n" +
                        "\"Больший из вас да будет вам слуга:\" (Мф.23:11) ")
                //.image(R.drawable.ic_baseline_accessibility_new_24)
                .background(R.color.introActivity6)
                .backgroundDark(R.color.introActivityButton)
                .scrollable(true)
                .build());
    }
}
