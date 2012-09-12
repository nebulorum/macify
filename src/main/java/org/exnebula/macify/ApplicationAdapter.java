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

import com.apple.eawt.*;

public class ApplicationAdapter {

  private final Application application;

  public ApplicationAdapter(Object application) {
    this.application = (Application) application;
  }

  public static ApplicationAdapter getApplication() {
    return new ApplicationAdapter(Application.getApplication());
  }

  public void setQuitHandler(final QuitHandlerAdapter quitHandler) {
    application.setQuitHandler(new QuitHandler() {
      @Override
      public void handleQuitRequestWith(AppEvent.QuitEvent quitEvent, QuitResponse quitResponse) {
        quitHandler.handleQuitRequestWith(new QuitEventWrapper(quitEvent), new QuitResponseWrapper(quitResponse));
      }
    });
  }

  public void setAboutHandler(final AboutHandlerAdapter aboutHandler) {
    application.setAboutHandler(new AboutHandler() {
      @Override
      public void handleAbout(AppEvent.AboutEvent aboutEvent) {
        aboutHandler.handleAbout(new AboutEventWrapper(aboutEvent));
      }
    });
  }

  public void setPreferencesHandler(final PreferenceHandlerAdapter preferenceHandler) {
    application.setPreferencesHandler(new PreferencesHandler() {
      @Override
      public void handlePreferences(AppEvent.PreferencesEvent preferencesEvent) {
        preferenceHandler.handlePreferences(new PreferenceEventWrapper(preferencesEvent));
      }
    });
  }
}