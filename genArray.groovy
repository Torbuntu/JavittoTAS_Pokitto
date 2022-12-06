def list = []
240.times{list<<0}


byte[] fieldList = new byte[list.size()]
for(int i = 0; i < list.size(); i++){
    fieldList[i] = list[i].byteValue()
}


def f = new File("data/mdaled/field")
f << fieldList


def items = "1,6,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0".split(",")
.collect{Integer.parseInt(it)}

byte[] itemList = new byte[items.size()]
for(int j = 0; j < items.size(); j++){
    itemList[j] = items[j].byteValue()
}


def itemFile = new File("data/mdaled/items")
itemFile << itemList
