# L3 design pattern report

- **Firstname**: Sacha
- **Lastname**: Duvivier

## Mini Documentation - Final.MD

### Adding new command
Adding a new **command** is easy, you just need to add a new class that ``extends Command``
and add it to the ``commands.yaml`` file with the class path and its name
you just need 3 functions to implement: ``support, neededArgs, and execute`` that are really easy to implement
you can override the loading service and the writing service to add your own way to load and write data
```yaml
commands:
  - name: MyExampleCommand
    class: com.myexample.MyExampleCommand
```

### Adding new option
Adding a new option is easy, you just need to add a new class that ``extends Option`` : 
define a constructor that is empty and that calls the super constructor with the name,longname of the option.
and you'il need to implements the 4 functions ``optionName, description, args and required`` and finaly add it to the ``options.yaml`` file with the class path and its name

```yaml
options:
  - name: MyExampleOption
    class: com.myexample.MyExampleOption
```

### Adding new Extension / Loading Service / Writing Service
Adding a new Service/Extension is easy too, you just need to add a new class that 
``extends WriteService`` or ``extends LoadService`` depending on what you want to do

if you want to add a new way to load data you just need to add it to the ``services.yaml`` file
like this:
```yaml
load-services:
  - extension: txt
    class: com.myexample.TxtLoadService
```
and implement ``getTodos`` function
if you want to add a new way to write data you just need to add it to the ``services.yaml`` file
like this:
```yaml
write-services:
  - extension: txt
    class: com.myexample.TxtWriteService
```

### Adding a new Attribute for Todo
To add a new attribute to the Todo.class, you just need to add a new field to the Todo class and add the getter and setter for this field.

