package com.luizcarlospjr.junitmockitounittest.tricks;

import org.springframework.stereotype.Component;

@Component
public class InjectableWhateverClass {

    public int sum(int a, int b) {
        return a + b;
    }

    public int sub(int a, int b) {
        return a - b;
    }

}
