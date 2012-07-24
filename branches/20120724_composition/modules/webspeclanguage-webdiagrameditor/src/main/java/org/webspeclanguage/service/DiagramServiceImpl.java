package org.webspeclanguage.service;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.springframework.transaction.annotation.Transactional;
import org.webspeclanguage.model.Diagram;
import org.webspeclanguage.model.DiagramType;
import org.webspeclanguage.repository.DiagramRepository;

import com.common.model.User;

@Transactional
public class DiagramServiceImpl implements DiagramService {

  private DiagramRepository repository;
  
  protected DiagramServiceImpl() { }
  
  public DiagramServiceImpl(DiagramRepository repository) {
    Validate.notNull(repository);
    this.repository = repository;
  }
  
  @Override
  public void save(Diagram diagram) {
    this.repository.save(diagram);
  }
  
  @Override
  public List getIdsAndNamesOfDiagrams(User user, DiagramType type) {
    return this.repository.getIdsAndNamesOfDiagrams(user, type);
  }

  @Override
  public Diagram getDiagram(User user, long diagramId) {
    return this.repository.getDiagram(user, diagramId);
  }
}
