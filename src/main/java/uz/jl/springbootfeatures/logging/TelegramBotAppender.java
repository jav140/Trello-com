package uz.jl.springbootfeatures.logging;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.core.*;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.Serializable;

@Plugin(
        name = "TelegramBotAppender",
        category = Core.CATEGORY_NAME,
        elementType = Appender.ELEMENT_TYPE)

public class TelegramBotAppender extends AbstractAppender {

    private  ExampleBot exampleBot;
    protected TelegramBotAppender(String name, Filter filter) {

        super(name, filter, PatternLayout.newBuilder().build(),true,null);
    }

    @PluginFactory
    public static TelegramBotAppender createAppender(@PluginAttribute("name") String name, @PluginElement("Filter") Filter filter) {
        return new TelegramBotAppender(name, filter);
    }

    @Override
    public void append(LogEvent event) {
        exampleBot.sendInfo(String.valueOf(event.getMessage()));


//        System.out.println("--------------------------------------------------");
//        System.out.println(event.toString());
//        System.out.println("--------------------------------------------------");

    }
}
