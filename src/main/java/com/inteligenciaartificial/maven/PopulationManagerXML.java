package com.inteligenciaartificial.maven;

import org.jgap.Genotype;
import org.jgap.data.DataTreeBuilder;
import org.jgap.data.IDataCreators;
import org.jgap.xml.XMLDocumentBuilder;
import org.jgap.xml.XMLManager;
import org.w3c.dom.Document;

import java.io.File;

public class PopulationManagerXML {
    public static void saveXMLPopulationEvolution(Genotype population) throws Exception{
        DataTreeBuilder builder = DataTreeBuilder.getInstance();
        IDataCreators doc2 = builder.representGenotypeAsDocument(population);
        XMLDocumentBuilder docBuilder = new XMLDocumentBuilder();
        Document xmlDoc = (Document) docBuilder.buildDocument(doc2);
        XMLManager.writeFile(xmlDoc, new File("PoblaciónDescuentoMáximo.xml"));
    }
}
