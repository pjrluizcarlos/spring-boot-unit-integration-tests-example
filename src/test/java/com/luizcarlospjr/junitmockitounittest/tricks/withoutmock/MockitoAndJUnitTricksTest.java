package com.luizcarlospjr.junitmockitounittest.tricks.withoutmock;

import com.luizcarlospjr.junitmockitounittest.tricks.InjectableWhateverClass;
import com.luizcarlospjr.junitmockitounittest.tricks.MockitoAndJUnitTricks;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MockitoAndJUnitTricksTest {

    private MockitoAndJUnitTricks mockitoAndJUnitTricks;
    private InjectableWhateverClass injectableWhateverClass;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        injectableWhateverClass = new InjectableWhateverClass();
        mockitoAndJUnitTricks = new MockitoAndJUnitTricks(injectableWhateverClass);
    }

    @Test
    public void willCryWithSumZero() {
        exception.expect(IllegalArgumentException.class);
        mockitoAndJUnitTricks.cryIfSumReturnZero(-3, 3);
    }

    @Test
    public void willNotCryWithSumNotZero() {
        mockitoAndJUnitTricks.cryIfSumReturnZero(-2, 3);
    }

}
