/*
 * Copyright (c) 2015-2017 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 */
'use strict';
/**
 * Defines a directive for factory item in list.
 * @author Oleksii Orel
 */
class CheFactoryItem {
    /**
     * Default constructor.
     */
    constructor() {
        this.restrict = 'E';
        this.templateUrl = 'app/factories/list-factories/factory-item/factory-item.html';
        this.replace = false;
        this.controller = 'FactoryItemController';
        this.controllerAs = 'factoryItemController';
        this.bindToController = true;
        // we require ngModel as we want to use it inside our directive
        this.require = ['ngModel'];
        this.scope = {
            factory: '=cdvyFactory',
            isChecked: '=cdvyChecked',
            isSelectable: '=cdvyIsSelectable',
            isSelect: '=?ngModel',
            onCheckboxClick: '&?cdvyOnCheckboxClick'
        };
    }
}
exports.CheFactoryItem = CheFactoryItem;
