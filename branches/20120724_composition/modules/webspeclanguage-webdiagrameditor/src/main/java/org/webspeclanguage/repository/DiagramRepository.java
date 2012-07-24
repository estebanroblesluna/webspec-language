package org.webspeclanguage.repository;

import java.util.List;

import org.apache.commons.lang.Validate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.webspeclanguage.model.Diagram;
import org.webspeclanguage.model.DiagramType;

import com.common.model.User;

@org.springframework.stereotype.Repository
public class DiagramRepository {

  private SessionFactory sessionFactory;

  @Autowired
  public DiagramRepository(SessionFactory sessionFactory) {
    Validate.notNull(sessionFactory);
    this.sessionFactory = sessionFactory;
  }

  public Session getSession() {
    return this.sessionFactory.getCurrentSession();
  }

  public void save(Diagram object) {
    this.getSession().saveOrUpdate(object);
  }
  
  public List getIdsAndNamesOfDiagrams(User user, DiagramType type) {
    ProjectionList proList = Projections.projectionList();
    proList.add(Projections.property("id"));
    proList.add(Projections.property("name"));
    
    return this.getSession()
      .createCriteria(Diagram.class)
      .add(Restrictions.eq("owner", user))
      .add(Restrictions.eq("type", type))
      .setProjection(proList)
      .list();
  }

  public Diagram getDiagram(User user, long diagramId) {
    return (Diagram) this.getSession()
            .createCriteria(Diagram.class)
            .add(Restrictions.eq("owner", user))
            .add(Restrictions.eq("id", diagramId))
            .uniqueResult();
  }

}
