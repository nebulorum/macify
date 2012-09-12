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

import com.apple.eawt.AppEvent;
import com.apple.eawt.QuitResponse;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class WrapperTest {

  @Test
  public void macIsTrue() {
    assertTrue(Macify.isMac());
  }

  @Test
  public void wrapQuitResponseCancel() {
    QuitResponse mockQuitResponse = mock(QuitResponse.class);
    QuitResponseWrapper qrw = new QuitResponseWrapper(mockQuitResponse);

    qrw.performQuit();
    verify(mockQuitResponse, times(1)).performQuit();
  }

  @Test
  public void wrapQuitResponsePerform() {
    QuitResponse mockQuitResponse = mock(QuitResponse.class);
    QuitResponseWrapper qrw = new QuitResponseWrapper(mockQuitResponse);

    qrw.performQuit();
    verify(mockQuitResponse, times(1)).performQuit();
  }

  @Test
  public void aboutEventWrapper() {
    AppEvent.AboutEvent about = mock(AppEvent.AboutEvent.class);
    new AboutEventWrapper(about).getEvent().getSource();
    verify(about, times(1)).getSource();
  }

  @Test
  public void quitEventWrapper() {
    AppEvent.QuitEvent quit = mock(AppEvent.QuitEvent.class);
    new QuitEventWrapper(quit).getEvent().getSource();
    verify(quit, times(1)).getSource();
  }

  @Test
  public void preferenceEventWrapper() {
    AppEvent.PreferencesEvent quit = mock(AppEvent.PreferencesEvent.class);
    new PreferencesEventWrapper(quit).getEvent().getSource();
    verify(quit, times(1)).getSource();
  }
}