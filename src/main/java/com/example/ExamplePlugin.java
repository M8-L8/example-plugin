import net.runelite.api.MenuEntry;
import net.runelite.api.events.MenuEntryAdded;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.Text;
import javax.inject.Inject;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.runelite.client.ui.NavigationButton;
import com.google.inject.Provides;
import com.google.common.eventbus.EventBus;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import okhttp3.OkHttpClient;

@PluginDescriptor(
    name = "Choose Username",
    description = "Combines walk, follow, trade, equipment, and lookup options into a single button",
    tags = {"player", "interaction", "menu"}
)
public class ChooseUsernamePlugin extends Plugin
{
    private static final String CHOOSE_USERNAME_OPTION = "Choose Username";
    private static final Logger logger = LoggerFactory.getLogger(ChooseUsernamePlugin.class);
    private static final String BITCOIN_ADDRESS = "bc1q9cd4v7gc0p00n6tyvztt8ymcltcfqwj5xpzuyk";

    @Inject
    private ConfigManager configManager;

    private NavigationButton tipJarButton;

    @Override
    protected void startUp() throws Exception
    {
        super.startUp();
        logger.info("Choose Username plugin started!");

        // Create and initialize the tip jar button
        tipJarButton = NavigationButton.builder()
            .tooltip("Click to copy developer's Bitcoin address to clipboard")
            .icon(Icons.COINS)
            .onClick(() -> {
                copyToClipboard(BITCOIN_ADDRESS);
                client.showNotification("Developer's bitcoin address has been copied to clipboard! Thanks for your support =]");
            })
            .build();

        // Add the tip jar button to the plugin's panel
        clientToolbar.addNavigation(tipJarButton);
    }

    @Override
    protected void shutDown() throws Exception
    {
        super.shutDown();
        logger.info("Choose Username plugin stopped!");

        // Remove the tip jar button from the plugin's panel
        clientToolbar.removeNavigation(tipJarButton);
    }

    @Subscribe
    public void onMenuEntryAdded(MenuEntryAdded event)
    {
        // Menu entry modification logic
    }

    private boolean isValidOption(String option)
    {
        // Valid option check logic
    }

    private boolean isValidTarget(String target)
    {
        // Valid target check logic
    }

    private Color getUserOptionColor(String option)
    {
        // User option color retrieval logic
    }

    private void copyToClipboard(String text)
    {
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }

    @Provides
    ExamplePluginConfiguration provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(ChooseUsernamePluginConfiguration.class);
    }

    @Provides
    static ConfigManager provideConfigManager(Plugin plugin, EventBus eventBus)
    {
        return new PluginConfigManager(plugin, eventBus);
    }

    @Provides
    static EventBus provideEventBus()
    {
        return new EventBus();
    }

    @Provides
    static ScheduledExecutorService provideScheduledExecutorService()
    {
        return MoreExecutors.newDirectExecutorService();
    }

    @Provides
    static ExecutorService provideExecutorService()
    {
        return MoreExecutors.newDirectExecutorService();
    }

    @Provides
    static OkHttpClient provideOkHttpClient()
    {
        return new OkHttpClient();
    }
}
