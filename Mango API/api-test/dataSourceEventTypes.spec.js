/**
 * Copyright 2017 Infinite Automation Systems Inc.
 * http://infiniteautomation.com/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

const config = require('@infinite-automation/mango-client/test/setup');

describe('Data source event types', () => {
    before('Login', config.login);

    it('Get Virtual data source default event types', () => {
      return client.restRequest({
          path: '/rest/v2/data-source-event-types/VIRTUAL',
          method: 'GET'
      }).then(response => {
        assert.strictEqual(response.data.length, 1);
        assert.strictEqual(response.data[0].referenceId2, 1);
        assert.strictEqual(response.data[0].code, 'POLL_ABORTED');
        assert.strictEqual(response.data[0].descriptionKey, 'event.ds.pollAborted');
        assert.strictEqual(response.data[0].description, 'Poll aborted');
        assert.strictEqual(response.data[0].defaultAlarmLevel, 'URGENT');
      });
    });
    
    it('Fails to get unknown data source default event types', () => {
        return client.restRequest({
            path: '/rest/v2/data-source-event-types/UNKNOWN',
            method: 'GET'
        }).then(response => {
            throw new Error('Should not get any event types');
        }).catch(response => {
            if(typeof response.response === 'undefined')
                throw response;
            assert.equal(response.response.statusCode, 404);
        });
    });
});
