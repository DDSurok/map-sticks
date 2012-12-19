/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.ddsurok.datamodel;

/**
 *
 * @author d.duritskij
 */

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Session implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private UUID Id = null;
    private Integer AuthToken = null;
    private User user = null;
    private Date CreateTime = null;
    private Date LastUpdateTime = null;
    private Integer LiveTime = null;
    
    public void setId(UUID id) {
        Id = id;
    }
    
    public UUID getId() {
        return Id;
    }
    
    public void setAuthToken(Integer value) {
        AuthToken = value;
    }
    
    public Integer getAuthToken() {
        return AuthToken;
    }
    
    public void setUser (User value) {
        user = value;
    }
    
    public User getUser () {
        return user;
    }
    
    public void setCreateTime (Date value) {
        CreateTime = value;
    }
    
    public Date getCreateTime () {
        return CreateTime;
    }
    
    public void setLastUpdateTime (Date value) {
        LastUpdateTime = value;
    }
    
    public Date getLastUpdateTime () {
        return LastUpdateTime;
    }
    
    public void setLiveTime (Integer value) {
        LiveTime = value;
    }
    
    public Integer getLiveTime () {
        return LiveTime;
    }
}
