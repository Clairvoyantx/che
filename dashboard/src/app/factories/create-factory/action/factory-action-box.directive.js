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
 * Defines a directive for displaying action box.
 * @author Florent Benoit
 */
class FactoryActionBox {
    /**
     * Default constructor that is using resource
     * @ngInject for Dependency injection
     */
    constructor() {
        this.restrict = 'E';
        this.templateUrl = 'app/factories/create-factory/action/factory-action-box.html';
        this.replace = false;
        this.controller = 'FactoryActionBoxController';
        this.controllerAs = 'factoryActionBoxCtrl';
        this.bindToController = true;
        // scope values
        this.scope = {
            lifecycle: '@cdvyLifecycle',
            actionTitle: '@?cdvyActionTitle',
            callbackController: '=cdvyCallbackController',
            factoryObject: '=cdvyFactoryObject',
            onChange: '&cdvyOnChange'
        };
    }
}
exports.FactoryActionBox = FactoryActionBox;
