package org.webspeclanguage.repository;

import java.util.List;

import net.sf.cglib.core.CollectionUtils;
import net.sf.cglib.core.Predicate;

import org.apache.commons.lang.Validate;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.webspeclanguage.model.Diagram;
import org.webspeclanguage.model.DiagramType;
import org.webspeclanguage.model.Mockup;
import org.webspeclanguage.model.Project;
import org.webspeclanguage.model.Sprint;
import org.webspeclanguage.model.UserStory;

import com.common.model.User;

@org.springframework.stereotype.Repository
@SuppressWarnings("unchecked")
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
  
  public List<Diagram> getIdsAndNamesOfDiagrams(User user, DiagramType type) {
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

  public List<Project> getProjectsFor(User user) {
    Criteria criteria = this.getSession().createCriteria(Project.class);
    criteria.createAlias("users", "user", CriteriaSpecification.LEFT_JOIN);
    criteria.add(Restrictions.eq("user.id", user.getId()));
    criteria.setResultTransformer(Criteria.ROOT_ENTITY);
    return criteria.list();
  }

  public Project getProjectForUser(Long projectId, User user) {
    Criteria criteria = this.getSession().createCriteria(Project.class);
    criteria.createAlias("users", "user", CriteriaSpecification.LEFT_JOIN);
    criteria.add(Restrictions.eq("user.id", user.getId()));
    criteria.add(Restrictions.eq("id", projectId));
    criteria.setResultTransformer(Criteria.ROOT_ENTITY);
    List<Project> list = criteria.list();
    return (Project) list.get(0);
  }

  public void save(Project project) {
    this.getSession().saveOrUpdate(project);
  }

  public void save(Mockup m) {
    this.getSession().saveOrUpdate(m);
  }

  public Mockup getMockup(Long mockupId) {
    return (Mockup) this.getSession().get(Mockup.class, mockupId);
  }

  public void save(UserStory us) {
    this.getSession().saveOrUpdate(us);
  }

  public void save(Sprint s) {
    this.getSession().saveOrUpdate(s);
  }

  public UserStory getUserStory(Long userStoryId) {
    return (UserStory) this.getSession().get(UserStory.class, userStoryId);
  }

  public Sprint getSprint(Long sprintId) {
    return (Sprint) this.getSession().get(Sprint.class, sprintId);
  }

  public Project getProject(Long projectId) {
    return (Project) this.getSession().get(Project.class, projectId);
  }
}
