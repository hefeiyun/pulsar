/**
 * Copyright 2016 Yahoo Inc.
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
package com.yahoo.pulsar.zookeeper;

import static org.testng.Assert.assertEquals;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.yahoo.pulsar.common.policies.data.Policies;

@Test
public class DeserializersTest {

    @BeforeMethod
    void setup() throws Exception {
    }

    @AfterMethod
    void teardown() throws Exception {
    }

    @Test
    void testSimpleStringDeserialize() throws Exception {
        String key = "test_key";
        byte[] content = "test_content".getBytes("UTF-8");
        String result = Deserializers.STRING_DESERIALIZER.deserialize(key, content);
        assertEquals(result, "test_content");
    }

    @Test
    void testSimplePolicyDeserialize() throws Exception {
        String key = "test_key";
        String jsonPolicy = "{\"auth_policies\":{\"namespace_auth\":{},\"destination_auth\":{}},\"replication_clusters\":[],"
                + "\"bundles_activated\":true,\"backlog_quota_map\":{},\"persistence\":null,\"latency_stats_sample_rate\":{},\"message_ttl_in_seconds\":0}";
        byte[] content = jsonPolicy.getBytes("UTF-8");
        Policies result = Deserializers.POLICY_DESERIALIZER.deserialize(key, content);
        assertEquals(result, new Policies());
    }
}
