/*
 * This file is part of Hopsworks
 * Copyright (C) 2019, Logical Clocks AB. All rights reserved
 *
 * Hopsworks is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 *
 * Hopsworks is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * PURPOSE.  See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 */

package io.hops.hopsworks.common.dao.featurestore.storage_connectors.external_sql_query;

import io.hops.hopsworks.common.dao.featurestore.feature.FeaturestoreFeature;
import io.hops.hopsworks.common.dao.featurestore.storage_connectors.jdbc.FeaturestoreJdbcConnector;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;

/**
 * Entity class representing the feature_store_external_sql_query table in Hopsworks database.
 * An instance of this class represents a row in the database.
 */
@Entity
@Table(name = "feature_store_external_sql_query", catalog = "hopsworks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeaturestoreExternalSQLQuery.findAll", query = "SELECT fsq FROM " +
      "FeaturestoreExternalSQLQuery fsq"),
    @NamedQuery(name = "FeaturestoreExternalSQLQuery.findById",
        query = "SELECT fsq FROM FeaturestoreExternalSQLQuery fsq WHERE fsq.id = :id")})
public class FeaturestoreExternalSQLQuery implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;
  @Basic(optional = false)
  @Column(name = "query")
  private String query;
  @Column(name = "description")
  private String description;
  @Basic(optional = false)
  @Column(name = "name")
  private String name;
  @JoinColumn(name = "jdbc_connector_id", referencedColumnName = "id")
  private FeaturestoreJdbcConnector featurestoreJdbcConnector;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "featurestoreExternalSQLQuery")
  private Collection<FeaturestoreFeature> features;

  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
  
  public String getQuery() {
    return query;
  }
  
  public void setQuery(String query) {
    this.query = query;
  }
  
  public String getDescription() {
    return description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public FeaturestoreJdbcConnector getFeaturestoreJdbcConnector() {
    return featurestoreJdbcConnector;
  }
  
  public void setFeaturestoreJdbcConnector(
    FeaturestoreJdbcConnector featurestoreJdbcConnector) {
    this.featurestoreJdbcConnector = featurestoreJdbcConnector;
  }
  
  public Collection<FeaturestoreFeature> getFeatures() {
    return features;
  }
  
  public void setFeatures(Collection<FeaturestoreFeature> features) {
    this.features = features;
  }
  
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FeaturestoreExternalSQLQuery)) {
      return false;
    }
    
    FeaturestoreExternalSQLQuery that = (FeaturestoreExternalSQLQuery) o;
    
    if (!id.equals(that.id)) {
      return false;
    }
    if (!query.equals(that.query)) {
      return false;
    }
    if (!description.equals(that.description)) {
      return false;
    }
    if (!name.equals(that.name)) {
      return false;
    }
    if (features != null)
      if (!features.equals(that.features)) return false;
    return featurestoreJdbcConnector.equals(that.featurestoreJdbcConnector);
  }
  
  @Override
  public int hashCode() {
    int result = id.hashCode();
    result = 31 * result + query.hashCode();
    result = 31 * result + description.hashCode();
    result = 31 * result + name.hashCode();
    result = 31 * result + featurestoreJdbcConnector.hashCode();
    result = 31 * result + (features != null ? features.hashCode() : 0);
    return result;
  }
}
