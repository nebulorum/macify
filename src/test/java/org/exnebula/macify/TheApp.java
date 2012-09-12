package org.exnebula.macify;
/*
 * Copyright (C) 2012-2012 - Thomas Santana <tms@exnebula.org>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

import javax.swing.*;
import java.awt.*;

public class TheApp extends JFrame {

  public TheApp() {
    setTitle("TheApp");
    setPreferredSize(new Dimension(300, 300));
    add(new JLabel("Check Mac preferences, about and close"));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    createMenuBar();
    if (Macify.isMac()) {
      ApplicationAdapter app = ApplicationAdapter.getApplication();
      registerQuitHandler(app);
      registerAboutHandler(app);
      registerPreferenceHandler(app);
    }
  }

  private void createMenuBar() {
    JMenuBar menuBar = new JMenuBar();
    JMenu menu = new JMenu("Some Menu");
    JMenuItem item = new JMenuItem("Some Item");
    menu.add(item);
    menuBar.add(menu);
    setJMenuBar(menuBar);
  }

  private void registerAboutHandler(ApplicationAdapter app) {
    app.setAboutHandler(new AboutHandlerAdapter() {
      @Override
      public void handleAbout(AboutEventWrapper aboutEventWrapper) {
        JOptionPane.showMessageDialog(null, "So you want to know about me", "About", JOptionPane.INFORMATION_MESSAGE);
      }
    });
  }

  private void registerPreferenceHandler(ApplicationAdapter app) {
    app.setPreferencesHandler(new PreferencesHandlerAdapter() {
      @Override
      public void handlePreferences(PreferencesEventWrapper preferenceEvent) {
        JOptionPane.showMessageDialog(null, "What do you want?", "Preferences", JOptionPane.INFORMATION_MESSAGE);
      }
    });
  }

  private void registerQuitHandler(ApplicationAdapter app) {
    app.setQuitHandler(new QuitHandlerAdapter() {

      @Override
      public void handleQuitRequestWith(QuitEventWrapper quitEvent, QuitResponseWrapper quitResponse) {
        int ret = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit?", "Warning",
            JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (ret == JOptionPane.YES_OPTION)
          quitResponse.performQuit();
        else
          quitResponse.cancelQuit();
      }
    });
  }

  public static void main(String[] args) {
    System.setProperty("apple.laf.useScreenMenuBar", "true");
    TheApp app = new TheApp();
    app.pack();
    app.setVisible(true);
  }
}