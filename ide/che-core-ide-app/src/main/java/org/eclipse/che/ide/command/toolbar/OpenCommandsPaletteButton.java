/*******************************************************************************
 * Copyright (c) 2012-2017 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package org.eclipse.che.ide.command.toolbar;

import elemental.dom.Element;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;

import org.eclipse.che.ide.api.action.Action;
import org.eclipse.che.ide.api.action.ActionManager;
import org.eclipse.che.ide.api.keybinding.KeyBindingAgent;
import org.eclipse.che.ide.command.palette.CommandsPalettePresenter;
import org.eclipse.che.ide.command.palette.PaletteMessages;
import org.eclipse.che.ide.command.palette.ShowCommandsPaletteAction;
import org.eclipse.che.ide.ui.Tooltip;
import org.eclipse.che.ide.ui.menubutton.MenuPopupButton;
import org.eclipse.che.ide.ui.menubutton.MenuPopupItemDataProvider;
import org.eclipse.che.ide.ui.menubutton.PopupItem;
import org.eclipse.che.ide.util.Pair;
import org.eclipse.che.ide.util.input.CharCodeWithModifiers;
import org.eclipse.che.ide.util.input.KeyMapUtil;

import java.util.List;

import static org.eclipse.che.ide.ui.menu.PositionController.HorizontalAlign.MIDDLE;
import static org.eclipse.che.ide.ui.menu.PositionController.VerticalAlign.BOTTOM;

/** Button for opening Commands Palette. */
class OpenCommandsPaletteButton extends MenuPopupButton {

    private final Provider<ActionManager>   actionManagerProvider;
    private final Provider<KeyBindingAgent> keyBindingAgentProvider;

    @Inject
    OpenCommandsPaletteButton(Provider<CommandsPalettePresenter> commandsPalettePresenterProvider,
                              PaletteMessages messages,
                              Provider<ActionManager> actionManagerProvider,
                              Provider<KeyBindingAgent> keyBindingAgentProvider,
                              Provider<ShowCommandsPaletteAction> showCommandsPaletteActionProvider,
                              @Assisted SafeHtml content) {
        super(content, new MenuPopupItemDataProvider() {
            @Override
            public PopupItem getDefaultItem() {
                return null;
            }

            @Override
            public List<PopupItem> getItems() {
                return null;
            }

            @Override
            public boolean isGroup(PopupItem item) {
                return false;
            }

            @Override
            public Pair<List<PopupItem>, String> getChildren(PopupItem parent) {
                return null;
            }

            @Override
            public void setItemDataChangedHandler(ItemDataChangeHandler handler) {
            }
        });

        this.actionManagerProvider = actionManagerProvider;
        this.keyBindingAgentProvider = keyBindingAgentProvider;

        addClickHandler(event -> commandsPalettePresenterProvider.get().showDialog());

        // Postpone tooltip creation to avoid cyclic dependency with showCommandsPaletteAction.
        // Provider doesn't help in this case because showCommandsPaletteAction called in the constructor.
        Scheduler.get().scheduleDeferred(() -> Tooltip.create((Element)getElement(),
                                                              BOTTOM,
                                                              MIDDLE,
                                                              messages.actionShowPaletteTitle() + " " +
                                                              getHotKey(showCommandsPaletteActionProvider.get())));
    }

    private String getHotKey(Action action) {
        final String actionId = actionManagerProvider.get().getId(action);
        final CharCodeWithModifiers keyBinding = keyBindingAgentProvider.get().getKeyBinding(actionId);
        final String hotKey = KeyMapUtil.getShortcutText(keyBinding);

        if (hotKey == null) {
            return "";
        } else {
            return "[" + hotKey + "]";
        }
    }
}
