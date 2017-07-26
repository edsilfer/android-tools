import sys
from os import listdir
from os.path import isfile, join

# ADDS ROOT DIR TO PATH
sys.path.insert(0, '..')

ARG_SAMPLE_JAVA_FILE = "resources/CheckTipo.java"
ARG_CLASS_FILE_TEMPLATE = "resources/AutovalueClassTemplate.txt"
ARG_FORBIDDEN_SYMBOLS = ['@', '=', '(', ')', '!']
ARG_VARIABLE_DECLARATION_TEMPLATE = "@Nullable @SerializedName(\"{}\") public abstract {} {}();\n\t"


def get_java_file():
    return ARG_SAMPLE_JAVA_FILE


def contains(word, line):
    if word not in line:
        return False
    return True


def has_forbidden_symbols(line):
    for symbol in ARG_FORBIDDEN_SYMBOLS:
        if contains(symbol, line):
            return True
    return False


def is_variable_declaration(line):
    parts = line.strip().split()
    if len(parts) == 3 and not has_forbidden_symbols(line):
        return True
    return False


def get_variable_name(line):
    return line.rsplit(None, 1)[-1][:-1]


def get_variable_types(line):
    words = line.split()
    type = words[1]

    if type == 'int':
        return 'Integer'
    elif type == 'boolean':
        return 'Boolean'

    return type


def get_class_name(line):
    words = line.split()
    return words[words.index("class") + 1]


def get_class_package(line):
    words = line.split()
    return words[words.index("package") + 1][:-1]


def get_variables_name(content):
    with content as lines:
        vars = []
        types = []
        _class = "Json{}"
        package = ""
        for line in lines:
            if is_variable_declaration(line):
                vars.append(get_variable_name(line))
                types.append(get_variable_types(line))
            elif contains("class", line):
                _class = _class.format(get_class_name(line))
            elif contains ("package", line):
                package = get_class_package(line)

    return {'package': package, 'class': _class, 'variables': vars, 'types': types}


def get_autovalue_class_template ():
    with open(ARG_CLASS_FILE_TEMPLATE, 'rb') as file:
        return "".join(file.read())


def declare_variables (variables, types):
    declaration = ""
    for name, type in zip(variables, types):
        declaration += ARG_VARIABLE_DECLARATION_TEMPLATE.format(name, type, name)
    return declaration


def save_to_file (content, name):
    file = open ("output/{}.java".format(name), 'w')
    file.write(content)
    file.close()


def convert_to_autovalue(path):
    with open(path) as file:
        template = get_autovalue_class_template()
        components = get_variables_name(file)

        package = components['package']
        _class = components['class']
        variables = components['variables']
        types = components['types']

        save_to_file(template.format(package, _class, declare_variables(variables, types), _class, _class), _class)


# MAIN
path = sys.argv[1]
for file in [f for f in listdir(path) if isfile(join(path, f))]:
    path = "{}/{}".format(sys.argv[1], file)
    convert_to_autovalue(path)



