package com.amm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * Created by liuminhang on 16/8/8.
 */
@Controller
public class AMMUserController {
    @Autowired
    private AMMUserDetailsService ammUserDetailsService;


}
