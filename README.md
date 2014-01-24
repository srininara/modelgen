modelgen
========

A simple library which allows generation of model objects based on provided contract (as interface) and rules (as annotations). Useful for creating mock data for tests (performance tests primarily).

com.nacnez.util.modelgen.ModelGenerator : The main interface which provides a fluent API for generation of model objects
com.nacnez.util.modelgen.GenerationContract : Marker interface which any contract interface must extend
com.nacnez.util.modelgen.impl.SimpleModelGenerator : Simple implementation of the generator (currently the only available one).
com.nacnez.util.modelgen.GeneratorFactory : Factory to create ModelGenerator implementation (currently only support SimpleModelGenerator)
com.nacnez.util.modelgen.generator.rules : Package containing all supported genertion constraints/rules


Example usage:

	ModelGenerator<SimpleMockObject> smg = GeneratorFactory.get();
	Collection<SimpleMockObject> c = smg.make(1)
				.instancesWith(SimpleMockGenerationContract.class)
				.andProvideAsCollection();
				
More examples and details available as tests. Dependencies are defined in the pom file

Roadmap:
- Support to fill map (void andFillUpThis(Map<? extends Object,T> m);)
- Support for collections (of other things - wrapper objects and customer model objects) contained in the model

More to come
