    File file = new File("fs.img")
    byte[] binaryContent = file.bytes
	println "Size: ${binaryContent.size()}"
    println binaryContent
