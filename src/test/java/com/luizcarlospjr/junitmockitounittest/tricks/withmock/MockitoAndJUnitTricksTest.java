package com.luizcarlospjr.junitmockitounittest.tricks.withmock;

import com.luizcarlospjr.junitmockitounittest.tricks.InjectableWhateverClass;
import com.luizcarlospjr.junitmockitounittest.tricks.MockitoAndJUnitTricks;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MockitoAndJUnitTricksTest {

    private MockitoAndJUnitTricks mockitoAndJUnitTricks;
    private InjectableWhateverClass injectableWhateverClass;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        injectableWhateverClass = mock(InjectableWhateverClass.class);
        mockitoAndJUnitTricks = new MockitoAndJUnitTricks(injectableWhateverClass);
    }

    @Test
    public void willCryWithSumZero() {
        exception.expect(IllegalArgumentException.class);

        given(injectableWhateverClass.sum(-9, 14)).willReturn(0);
        mockitoAndJUnitTricks.cryIfSumReturnZero(-9, 14);
        verify(injectableWhateverClass).sum(-9, 14);
    }

    @Test
    public void willNotCryWithSumNotZero() {
        given(injectableWhateverClass.sum(0, 0)).willReturn(10);

        mockitoAndJUnitTricks.cryIfSumReturnZero(0, 0);

        verify(injectableWhateverClass).sum(0, 0);
    }

}
