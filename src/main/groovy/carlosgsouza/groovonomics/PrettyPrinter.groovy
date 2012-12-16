package carlosgsouza.groovonomics

class PrettyPrinter {
	
	def classDataFolder = new File("/Users/carlosgsouza/Dropbox/UFMG/Mestrado/mes/groovonomics/data/classes")
	def agregateDataFolder = new File("/Users/carlosgsouza/Dropbox/UFMG/Mestrado/mes/groovonomics/data/class_agregate")
	
	def classDataFactory = new ClassDataFactory()
	
	def all() {
		def allFile = new File(agregateDataFolder, "all.json")
		def data = classDataFactory.fromJsonFile allFile
		
		printDeclarationCount(data.publicMethodReturn, "Retorno de m�todo", "p�blico")
		printDeclarationCount(data.privateMethodReturn, "Retorno de m�todo", "privado")
		printDeclarationCount(data.protectedMethodReturn, "Retorno de m�todo", "protected")
		printDeclarationCount(data.publicField, "Campo", "p�blico")
		printDeclarationCount(data.privateField, "Campo", "privado")
		printDeclarationCount(data.protectedField, "Campo", "protected")
		printDeclarationCount(data.publicMethodParameter, "Par�metro de m�todo", "p�blico")
		printDeclarationCount(data.privateMethodParameter, "Par�metro de m�todo", "privado")
		printDeclarationCount(data.protectedMethodParameter, "Par�metro de Mmtodo", "protected")
		printDeclarationCount(data.pureTypeSystemPublicMethods, "M�todo com sistema de tipos �nico", "p�blico")
		printDeclarationCount(data.pureTypeSystemPrivateMethods, "M�todo com sistema de tipos �nico", "privado")
		printDeclarationCount(data.pureTypeSystemProtectedMethods, "M�todo com Sistema de Tipos �nico", "protected")
		printDeclarationCount(data.publicConstructorParameter, "Construtor", "p�blico")
		printDeclarationCount(data.privateConstructorParameter, "Construtor", "privado")
		printDeclarationCount(data.protectedConstructorParameter, "Construtor", "protected")
		printDeclarationCount(data.pureTypeSystemPublicConstructors, "Construtor com sistema de tipos �nico", "p�blico")
		printDeclarationCount(data.pureTypeSystemPrivateConstructors, "Construtor com sistema de tipos �nico", "privado")
		printDeclarationCount(data.pureTypeSystemProtectedConstructors, "Construtor com sistema de tipos �nico", "protected")
		printDeclarationCount(data.localVariable, "Vari�vel Local ou vari�vel de closure")
	}
	
	def printDeclarationCount(declarationCount, name) {
		printDeclarationCount(declarationCount, name, "")
	}
	def printDeclarationCount(declarationCount, name, attribute) {
		println "$name\t$attribute\t${declarationCount.s}\t${declarationCount.d}"
	}
	
	public static void main(args) {
		new PrettyPrinter().all()
	}
}
