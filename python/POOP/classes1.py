class Person():
    number=0
    def __init__(self,name,age):
        self.name=name
        self.age=age
        Person.number=Person.number+1
    
    @classmethod
    def get_infos(cls):
        print(cls.number)

class Schiavo(Person):
    def __init__(self, name, age, ID):
        super().__init__(name, age)
        self.ID=ID

s1=Schiavo("dodo",10,3535)
Person.get_infos()


