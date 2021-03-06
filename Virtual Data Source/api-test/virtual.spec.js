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

describe('Test Virtual data source', function() {
    before('Login', config.login);

    it('Create virtual data source', () => {

      const vrtDs = new DataSource({
          xid: "test_virtual",
          name: "virtual",
          enabled: true,
          modelType: "VIRTUAL",
          validationMessages: [],
          pollPeriod: {
            periods: 5,
            type: "MINUTES"
          },
          polling: false,
          editPermission: "edit-test",
          purgeSettings: {
            override: false,
            frequency: {
              periods: 1,
              type: "YEARS"
            }
          },
          alarmLevels: {
            POLL_ABORTED: "URGENT"
          }
      });

      return vrtDs.save().then((savedDs) => {
        assert.equal(savedDs.xid, 'test_virtual');
        assert.equal(savedDs.name, 'virtual');
        assert.equal(savedDs.enabled, true);
        assert.equal(savedDs.polling, false);
        assert.equal(savedDs.pollPeriod.periods, 5);
        assert.equal(savedDs.pollPeriod.type, "MINUTES");

        assert.equal(savedDs.editPermission, "edit-test");
        assert.isNumber(savedDs.id);
      });
    });

    it('Create virtual data point', () => {

        const trigger = new DataPoint({
              enabled: true,
              dataSourceXid: 'test_virtual',
              pointLocator: {
                startValue: "false",
                modelType: "PL.VIRTUAL",
                dataType: "BINARY",
                settable: true,
                changeType: "NO_CHANGE"
              },
              name: "virtual",
              xid: "virtual_no_change_binary"
            });

        return trigger.save().then((savedDp) => {
          assert.equal(savedDp.xid, 'virtual_no_change_binary');
          assert.equal(savedDp.name, 'virtual');
          assert.equal(savedDp.enabled, true);
          assert.equal(savedDp.pointLocator.startValue, "false");
          assert.equal(savedDp.pointLocator.settable, true);
          assert.isNumber(savedDp.id);
        });
      });

    it('Deletes the new virtual data source and its points', () => {
        return DataSource.delete('test_virtual');
    });
});
