import sys
import json
import subprocess

# ADDS ROOT DIR TO PATH
sys.path.insert(0, '..')

ARG_SAMPLE_JSON_FILE = "resources/WorkingDays.json"

CMD_SQL_INSERT = "adb shell sqlite3 {} \"INSERT INTO {} ({}) VALUES ({})\""
CMD_SQL_SELECT = "adb shell sqlite3 {} \"SELECT {} FROM {}\""
CMD_SQL_DELETE = "adb shell sqlite3 {} \"DELETE FROM {}\""

ACTION_INSERT = 'INSERT'
ACTION_DELETE = 'DELETE'
ACTION_SELECT = 'SELECT'

ERROR_INVALID_INPUT = "You must specify the table name, database full path and SQL action to be executed"
ERROR_INVALID_ACTION = "Unrecognized actions. Valid actions are: SELECT, DELETE and INSERT"


def get_columns_and_values(object):
    columns = ""
    values = ""

    for key, value in object.items():
        columns += str(key) + ","
        if is_number(value):
            values += str(value) + ","
        else:
            values += "'" + str(value) + "'" + ","

    return [columns[:-1], values[:-1].replace("False", '0').replace("True", '1')]


def is_number(s):
    try:
        int(s)
        return True
    except ValueError:
        return False


def get_json_file():
    return sys.argv[1]


def get_table_name():
    return sys.argv[2]


def get_database_path():
    return sys.argv[3]


def get_action():
    return sys.argv[4]


def validate_input():
    return len(sys.argv) == 5


def run_commands(columns, values):
    action = get_action()

    if action == ACTION_SELECT:
        formatted_command = CMD_SQL_SELECT.format(get_database_path(), columns, get_table_name())
    elif action == ACTION_DELETE:
        formatted_command = CMD_SQL_DELETE.format(get_database_path(), get_table_name())
    elif action == ACTION_INSERT:
        formatted_command = CMD_SQL_INSERT.format(get_database_path(), get_table_name(), columns, values)
    else:
        print ERROR_INVALID_ACTION
        return

    print "Executing command: {}".format(formatted_command)

    process = subprocess.Popen(
        formatted_command.split(),
        stdout=subprocess.PIPE
    )

    output, error = process.communicate()

    formatted_output = "OUTPUT: {}".format(output)
    formatted_error = "ERROR: {}".format(error)

    print formatted_output, formatted_error


if validate_input():
    with open(get_json_file()) as json_file:
        data = json.load(json_file)
        for object in data:
            normalizedRow = get_columns_and_values(object)
            run_commands(normalizedRow[0], normalizedRow[1])
else:
    print ERROR_INVALID_INPUT
