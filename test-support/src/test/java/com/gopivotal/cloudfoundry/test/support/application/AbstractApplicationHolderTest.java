/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gopivotal.cloudfoundry.test.support.application;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public final class AbstractApplicationHolderTest {

    private final Application application = mock(Application.class);

    private final AbstractApplicationHolder applicationHolder = new StubApplicationHolder();

    @Test
    public void test() {
        assertNull(this.applicationHolder.get());
        this.applicationHolder.set(this.application);
        assertSame(this.application, this.applicationHolder.get());
        this.applicationHolder.clear();
        assertNull(this.applicationHolder.get());
    }

    private static class StubApplicationHolder extends AbstractApplicationHolder {

    }

}