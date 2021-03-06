package edu.webapp.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.Cookies;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CaptionPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;

import edu.webapp.shared.WCSettings;
import edu.webapp.shared.WordCloud;

import java.util.Date;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class WordCloudApp implements EntryPoint
{
    /**
     * Create a remote service proxy to talk to the server-side Greeting
     * service.
     */
    private final WordCloudServiceAsync wcService = GWT.create(WordCloudService.class);
    private WCSettings setting = new WCSettings();

    /**
     * This is the entry point method.
     */
    public void onModuleLoad()
    {
        TextArea textArea = createTextArea();

        createCreateWordCloudButton(textArea);

        createLuckyButtons();

        createAdvancedArea();

        createShowAdvancedButton();
    }

    private void createShowAdvancedButton()
    {
        final String COOKIE_NAME = "show-adv-options";

        final Anchor showAdvancedButton = Anchor.wrap(Document.get().getElementById("adv_link"));
        final Panel settingArea = RootPanel.get("settingContainer");

        showAdvancedButton.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                if (showAdvancedButton.getText().equals("Show Advanced Options"))
                {
                    settingArea.removeStyleName("hide");
                    showAdvancedButton.setText("Hide Advanced Options");
                    Cookies.setCookie(COOKIE_NAME, "1", new Date(System.currentTimeMillis() + (86400 * 7 * 1000)));
                }
                else
                {
                    settingArea.addStyleName("hide");
                    showAdvancedButton.setText("Show Advanced Options");
                    Cookies.removeCookie(COOKIE_NAME);
                }
            }
        });

        boolean needToShow = "1".equals(Cookies.getCookie(COOKIE_NAME));
        if (needToShow)
            showAdvancedButton.fireEvent(new GwtEvent<ClickHandler>()
            {
                @Override
                public com.google.gwt.event.shared.GwtEvent.Type<ClickHandler> getAssociatedType()
                {
                    return ClickEvent.getType();
                }

                @Override
                protected void dispatch(ClickHandler handler)
                {
                    handler.onClick(null);
                }
            });
    }

    private void createLuckyButtons()
    {
        createLuckyWikiButton();
        createLuckyTwitterButton();
        createLuckyYoutubeButton();
        createLuckyGoogleButton();
    }

    private void createLuckyWikiButton()
    {
        Anchor rndWikiButton = Anchor.wrap(Document.get().getElementById("btn_rnd_wiki"));
        final TextArea textArea = TextArea.wrap(Document.get().getElementById("input_text"));
        rndWikiButton.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                wcService.getRandomWikiUrl(new AsyncCallback<String>()
                {
                    public void onSuccess(String result)
                    {
                        textArea.setText(result);
                    }

                    public void onFailure(Throwable caught)
                    {
                        textArea.setText("http://en.wikipedia.org/wiki/Special:random");
                    }
                });

            }
        });
    }

    private void createLuckyTwitterButton()
    {
        Anchor rndWikiButton = Anchor.wrap(Document.get().getElementById("btn_rnd_twitter"));
        final TextArea textArea = TextArea.wrap(Document.get().getElementById("input_text"));
        rndWikiButton.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                wcService.getRandomTwitterUrl(new AsyncCallback<String>()
                {
                    public void onSuccess(String result)
                    {
                        textArea.setText(result);
                    }

                    public void onFailure(Throwable caught)
                    {
                        textArea.setText("twitter: hot trend");
                    }
                });

            }
        });
    }

    private void createLuckyYoutubeButton()
    {
        Anchor rndWikiButton = Anchor.wrap(Document.get().getElementById("btn_rnd_youtube"));
        final TextArea textArea = TextArea.wrap(Document.get().getElementById("input_text"));
        rndWikiButton.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                wcService.getRandomYoutubeUrl(new AsyncCallback<String>()
                {
                    public void onSuccess(String result)
                    {
                        textArea.setText(result);
                    }

                    public void onFailure(Throwable caught)
                    {
                        textArea.setText("https://www.youtube.com");
                    }
                });

            }
        });
    }

    private void createLuckyGoogleButton()
    {
        Anchor rndGoogleButton = Anchor.wrap(Document.get().getElementById("btn_rnd_google"));
        final TextArea textArea = TextArea.wrap(Document.get().getElementById("input_text"));
        rndGoogleButton.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                wcService.getRandomGoogleUrl(new AsyncCallback<String>()
                {
                    public void onSuccess(String result)
                    {
                        textArea.setText(result);
                    }

                    public void onFailure(Throwable caught)
                    {
                        textArea.setText("google: hot trend");
                    }
                });

            }
        });
    }

    private void createAdvancedArea()
    {
        final CaptionPanel settingArea = new SettingsPanel().create(setting);
        settingArea.removeStyleName("gwt-DecoratorPanel");
        RootPanel.get("settingContainer").add(settingArea);
    }

    private void createCreateWordCloudButton(final TextArea textArea)
    {
        Button sendButton = Button.wrap(Document.get().getElementById("btn_create_wc"));
        sendButton.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                createWordCloud();
            }
        });
    }

    private TextArea createTextArea()
    {
        TextArea textArea = TextArea.wrap(Document.get().getElementById("input_text"));
        textArea.addKeyDownHandler(new KeyDownHandler()
        {
            public void onKeyDown(KeyDownEvent event)
            {
                event.preventDefault();
                if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
                {
                    createWordCloud();
                }
            }
        });

        return textArea;
    }

    private void createWordCloud()
    {
        TextArea textArea = TextArea.wrap(Document.get().getElementById("input_text"));
        String text = textArea.getText().trim();
        if (!text.isEmpty())
        {
            createWordCloud(text);
        }
        else
        {
            textArea.setFocus(true);
        }
    }

    private void createWordCloud(String text)
    {
        final DialogBox shadow = AppUtils.createShadow();
        shadow.center();
        shadow.show();

        final DialogBox loadingBox = AppUtils.createLoadingBox();
        loadingBox.show();
        loadingBox.center();

        wcService.buildWordCloud(text, setting, new AsyncCallback<WordCloud>()
        {
            public void onSuccess(WordCloud result)
            {
                loadingBox.hide();
                if (result != null)
                {
                    shadow.hide();
                    Window.Location.assign("/cloud.html?id=" + result.getId());
                }
                else
                {
                    //possibly empty text
                    DialogBox errorBox = AppUtils.createMessageBox("<h3>Your text must contain at least 10 words</h3>", shadow);
                    errorBox.center();
                    errorBox.show();
                }
            }

            public void onFailure(Throwable caught)
            {
                loadingBox.hide();
                DialogBox errorBox = AppUtils.createErrorBox(caught, shadow);
                errorBox.center();
                errorBox.show();
            }
        });
    }
}