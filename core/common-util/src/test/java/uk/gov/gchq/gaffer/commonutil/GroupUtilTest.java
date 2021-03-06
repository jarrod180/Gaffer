/*
 * Copyright 2017-2018 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.gchq.gaffer.commonutil;

import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class GroupUtilTest {
    private static final String INVALID_STRING = "inv@l1dStr|ng&^";
    private static final String VALID_STRING = "vAl1d-Str|ng";

    @Test
    public void shouldThrowExceptionWithInvalidStringName() {
        // When / Then
        try {
            GroupUtil.validateName(INVALID_STRING);
            fail("Exception expected");
        } catch (final IllegalArgumentException e) {
            assertTrue(e.getMessage().contains("Group is invalid"));
        }
    }

    @Test
    public void shouldPassValidationWithValidStringName() {
        // When
        GroupUtil.validateName(VALID_STRING);

        // Then - no exceptions
    }
}
