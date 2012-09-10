package org.webspeclanguage.service;

import java.util.List;

import org.webspeclanguage.model.Diagram;
import org.webspeclanguage.model.DiagramType;

import com.common.model.User;

public interface DiagramService {

  void save(Diagram diagram);

  List getIdsAndNamesOfDiagrams(User user, DiagramType type);

  Diagram getDiagram(User user, long diagramId);
}