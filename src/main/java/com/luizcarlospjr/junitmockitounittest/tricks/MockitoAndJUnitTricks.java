package com.luizcarlospjr.junitmockitounittest.tricks;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MockitoAndJUnitTricks {

    private InjectableWhateverClass injectableWhateverClass;

    public MockitoAndJUnitTricks(InjectableWhateverClass injectableWhateverClass) {
        this.injectableWhateverClass = injectableWhateverClass;
    }

    public void cryIfSumReturnZero(int a, int b) {
        if (injectableWhateverClass.sum(a, b) == 0) {
            log.error("OOOOOOOOOOOH MMMMMMMMMMMMMMYYYYYYYYYY :( :( :( :( :(");
            throw new IllegalArgumentException("Sum with return zero detected. #CRY");
        }
    }

}
