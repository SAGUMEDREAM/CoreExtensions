package com.KafuuChino0722.coreextensions.network;

import com.KafuuChino0722.coreextensions.Config;
import com.KafuuChino0722.coreextensions.util.Reference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Locale;

import static com.KafuuChino0722.coreextensions.util.Info.NETWORK;
import static com.KafuuChino0722.coreextensions.util.Info.UPDATER;

public class VersionChecker {
    public static int getVersion() {
        String versionUrl = "https://www.otomads.top/api/coreextensions_versions.txt";
        Locale currentLocale = Locale.getDefault();
        String language = currentLocale.getLanguage();

        try {
            URL url = new URL(versionUrl);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String remoteVersionStr = reader.readLine();
            reader.close();

            int remoteVersion = Integer.parseInt(remoteVersionStr);
            int currentVersion = Reference.VERSION_ID;

            return remoteVersion;
        } catch (IOException e) {
            e.printStackTrace();
            UPDATER.info("Could not connect to server.");
            return -100;
        }
    }
    public static void check() {
        if(Config.getConfigBoolean("CHECKING_FOR_UPDATE")) {
            String versionUrl = "https://www.otomads.top/api/coreextensions_versions.txt";
            Locale currentLocale = Locale.getDefault();
            String language = currentLocale.getLanguage();

            NETWORK.info("Checking for updates！");
            try {
                URL url = new URL(versionUrl);
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                String remoteVersionStr = reader.readLine();
                reader.close();

                int remoteVersion = Integer.parseInt(remoteVersionStr);
                int currentVersion = Reference.VERSION_ID;

                if (remoteVersion > currentVersion) {
                    NETWORK.info("A new version is available！");
                } else {
                    NETWORK.info("Your CoreExtensions are already the latest version.");
                }

            } catch (IOException e) {
                e.printStackTrace();
                NETWORK.info("Could not connect to server.");
            }
        }
    }
}
