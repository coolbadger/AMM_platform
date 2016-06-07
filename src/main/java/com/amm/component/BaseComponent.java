package com.amm.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by csw on 2016/5/26 18:08.
 * explain：
 */
public abstract class BaseComponent {

    protected final Logger logger;

    public BaseComponent() {
        this.logger = LoggerFactory.getLogger(this.getClass());
    }
}
