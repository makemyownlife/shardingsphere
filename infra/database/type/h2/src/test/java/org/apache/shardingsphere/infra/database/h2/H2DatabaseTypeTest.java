/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.infra.database.h2;

import org.apache.shardingsphere.infra.database.spi.DatabaseType;
import org.apache.shardingsphere.infra.util.enums.QuoteCharacter;
import org.apache.shardingsphere.infra.util.spi.type.typed.TypedSPILoader;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

class H2DatabaseTypeTest {
    
    @Test
    void assertGetQuoteCharacter() {
        assertThat(TypedSPILoader.getService(DatabaseType.class, "H2").getQuoteCharacter(), is(QuoteCharacter.QUOTE));
    }
    
    @Test
    void assertGetJdbcUrlPrefixes() {
        assertThat(TypedSPILoader.getService(DatabaseType.class, "H2").getJdbcUrlPrefixes(), is(Collections.singleton("jdbc:h2:")));
    }
    
    @Test
    void assertGetDataSourceMetaData() {
        assertThat(TypedSPILoader.getService(DatabaseType.class, "H2").getDataSourceMetaData("jdbc:h2:~:foo_ds", "sa"), instanceOf(H2DataSourceMetaData.class));
        assertThat(TypedSPILoader.getService(DatabaseType.class, "H2").getDataSourceMetaData("jdbc:h2:mem:foo_ds", "sa"), instanceOf(H2DataSourceMetaData.class));
    }
    
    @Test
    void assertGetSystemDatabaseSchemaMap() {
        assertTrue(TypedSPILoader.getService(DatabaseType.class, "H2").getSystemDatabaseSchemaMap().isEmpty());
    }
    
    @Test
    void assertGetSystemSchemas() {
        assertTrue(TypedSPILoader.getService(DatabaseType.class, "H2").getSystemSchemas().isEmpty());
    }
}
