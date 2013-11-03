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

package com.gopivotal.cloudfoundry.test.support.util;

/**
 * A template for retrying operations
 */
public final class RetryTemplate {

    private static final Integer DEFAULT_TIMEOUT = 60 * 1000;

    private static final Integer DEFAULT_INTERVAL = 5 * 1000;

    /**
     * Retry an operation with the default interval and timeout
     *
     * @param retryCallback the callback to be retried
     */
    public static void retry(RetryCallback retryCallback) {
        retry(DEFAULT_INTERVAL, DEFAULT_TIMEOUT, retryCallback);
    }

    /**
     * Retry an operation with a specified interval and timeout
     *
     * @param interval      how long the wait between tries
     * @param timeout       how long to keep trying for
     * @param retryCallback the callback to retry
     */
    public static void retry(Integer interval, Integer timeout, RetryCallback retryCallback) {
        Long finishTime = System.currentTimeMillis() + timeout;

        try {
            while (System.currentTimeMillis() < finishTime) {
                if (retryCallback.execute()) {
                    return;
                }

                Thread.sleep(interval);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        throw new IllegalStateException(retryCallback.getFailureMessage());
    }

}