package com.friend.spiritualcommunication;

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
                .title("Мир Вам! \n ДУХОВНЫЕ ХРИСТИАНСКИЕ ТЕМЫ ДЛЯ ОБЩЕНИЯ помогут Вам, кто:")
                .description("- ищет как послужить; \n \n" +
                        "- желает простого живого духовного общения вместе со Словом Божиим;\n \n" +
                        "- хочет духовно поддержать ближнего;\n \n" +
                        "- стремится духовно расти;\n \n" +
                        "- ищет дружбы и отношений;\n \n" +
                        "- хочет участвовать в ученичестве\n \n")
                //.image(R.drawable.ic_baseline_accessibility_new_24)
                .background(R.color.introActivity6)
                .backgroundDark(R.color.introActivityButton)
                .scrollable(true)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("Духовные темы состоят из мест Библии и вопросов, чтобы:")
                .description("- размышляя над Писанием, обратиться к СЕРДЦУ, а не знаниям человека, \n\n" +
                        "- направить на практическое применение Слова Божьего в своей жизни.")
                //.image(R.drawable.ic_baseline_accessibility_new_24)
                .background(R.color.introActivity6)
                .backgroundDark(R.color.introActivityButton)
                .scrollable(true)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("ВНАЧАЛЕ.")
                .description("* Помните, что в духовном общении с Вами присутствует Господь. \"Ибо, где двое или трое собраны во имя Мое, там Я посреди них.\" (Мф.18:20).\n" +
                        "* Выберите одну из тем, прочитайте первый стих внутри темы и затем по очереди отвечайте на следующие за стихом вопросы. Далее переходите к следующему стиху и вопросам.\n" +
                        "* В конце каждой темы предложен стих для запоминания, чтобы тема и общение остались в памяти.\n" +
                        "Рекомендуется заканчивать общение молитвой \uD83D\uDE4F\uD83C\uDFFD \n")
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
                        "Т.к. это не семинар, не лекция, и не закон.\n\n" +
                        "Вы можете \uD83D\uDE0A:\n" +
                        "- использовать и другие места Библии, подходящие к теме;\n" +
                        "- задавать Ваши вопросы, которые будут наиболее соответствующими к Вашему диалогу;\n" +
                        "- делиться тем, что происходило в Вашей жизни.\n" +
                        "Одним словом, вести общение так, как ведёт Дух Святой.\n" +
                        "А духовные темы в помощь Вам. :)")
                //.image(R.drawable.ic_baseline_accessibility_new_24)
                .background(R.color.introActivity6)
                .backgroundDark(R.color.introActivityButton)
                .scrollable(true)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("В общении рекомендуется:")
                .description("- отвечать на вопросы обоим (чтобы был ДИАЛОГ, а не монолог);\n" +
                        "- развивать дружеские и доверительные отношения (важна любовь к ближнему);\n" +
                        "- общаться на равных (чтобы не быть профессионалом и экспертом, учителем или начальником). \"Больший из вас да будет вам слуга:\" (Мф.23:11).\n" +
                        "\n" +
                        "Общение можно проводить как при личной встрече, так и по телефону")
                //.image(R.drawable.ic_baseline_accessibility_new_24)
                .background(R.color.introActivity6)
                .backgroundDark(R.color.introActivityButton)
                .scrollable(true)
                .build());

        addSlide(new SimpleSlide.Builder()
                .title("Приглашаю и ВАС стать ДУХОВНЫМ ДРУГОМ для ближнего!")
                .description("Благословений Вам! Стихи в пожелание Вам на все общения \uD83D\uDE07\uD83D\uDE4F\uD83C\uDFFD:\n" +
                        "\n" +
                        "\"Ибо, где двое или трое собраны во имя Мое, там Я посреди них.\" (Мф.18:20)\n" +
                        "\n" +
                        "\"А вы не называйтесь учителями, ибо один у вас Учитель - Христос, все же вы - братья;\" (Мф.23:8)\n" +
                        "\"Больший из вас да будет вам слуга:\" (Мф.23:11) \n" +
                        "\n" +
                        "\"... возлюби Господа Бога твоего всем сердцем твоим, и всею душею твоею, и всею крепостию твоею, и всем разумением твоим, и ближнего твоего, как самого себя.\" (Лк.10:27)\" ")
                //.image(R.drawable.ic_baseline_accessibility_new_24)
                .background(R.color.introActivity6)
                .backgroundDark(R.color.introActivityButton)
                .scrollable(true)
                .build());
    }
}
