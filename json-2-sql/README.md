# JSON to SQLite

During development time it is commons that you might want to add mocked data in order to test flows. A good way to store those mocked data - that oftem contains test cases - is having json files that match the structure of your database. The problem then becomes, how to add these data from the JSON to the SQLite database. An option is to have that on you code, but this wouldn't be very good since yu would be polluting your repo with mocked data.

That's why this script exists. With one single line you can add all data from a JSON file to your SQLite database. It works like that:

```bash
python json-parser.py full_path_to_json_file.json table_name full_path_to_database action
```

**Where:**
 - Supported actions are: SELECT, DELETE or INSERT;
 
---
Copyright 2017 Edgar da Silva Fernandes

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
