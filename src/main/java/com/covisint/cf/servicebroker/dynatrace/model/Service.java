package com.covisint.cf.servicebroker.dynatrace.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * 
 * Service class will be responsible for holding catalog, uuid, description , 
 * bindable flag , list of tags, serviceMetadata, plans and dashboardClient
 *
 * @version 1.0, 2015-06-01
 * @author Lingeshm
 *
 */

final class Service {

    private final Catalog catalog;

    private final Object monitor = new Object();

    private volatile UUID id;

    private volatile String name;

    private volatile String description;

    private volatile Boolean bindable;

    private volatile List<String> tags;

    private volatile ServiceMetadata serviceMetadata;

    private volatile List<String> requires;

    private volatile List<Plan> plans;

    private volatile DashboardClient dashboardClient;

    Service(Catalog catalog) {
        this.catalog = catalog;
    }

    UUID getId() {
        synchronized (this.monitor) {
            Assert.notNull(this.id, "Services must specify an id");
            return this.id;
        }
    }

    String getName() {
        synchronized (this.monitor) {
            Assert.notNull(this.name, "Services must specify a name");
            return this.name;
        }
    }

    String getDescription() {
        synchronized (this.monitor) {
            Assert.notNull(this.description, "Services must specify a description");
            return this.description;
        }
    }

    Boolean getBindable() {
        synchronized (this.monitor) {
            Assert.notNull(this.bindable);
            return this.bindable;
        }
    }

    List<String> getTags() {
        synchronized (this.monitor) {
            return this.tags;
        }
    }

    @JsonProperty("metadata")
    ServiceMetadata getServiceMetadata() {
        synchronized (this.monitor) {
            return this.serviceMetadata;
        }
    }

    List<String> getRequires() {
        synchronized (this.monitor) {
            return this.requires;
        }
    }

    List<Plan> getPlans() {
        synchronized (this.monitor) {
            return this.plans;
        }
    }

    @JsonProperty("dashboard_client")
    DashboardClient getDashboardClient() {
        synchronized (this.monitor) {
            return this.dashboardClient;
        }
    }

    Catalog and() {
        synchronized (this.monitor) {
            return this.catalog;
        }
    }

    Service id(UUID id) {
        synchronized (this.monitor) {
            this.id = id;
            return this;
        }
    }

    Service name(String name) {
        synchronized (this.monitor) {
            this.name = name;
            return this;
        }
    }

    Service description(String description) {
        synchronized (this.monitor) {
            this.description = description;
            return this;
        }
    }

    Service bindable(Boolean bindable) {
        synchronized (this.monitor) {
            this.bindable = bindable;
            return this;
        }
    }

    Service tags(String... tags) {
        synchronized (this.monitor) {
            if (this.tags == null) {
                this.tags = new ArrayList<>();
            }
            Arrays.stream(tags).forEach(this.tags::add);
            return this;
        }
    }

    ServiceMetadata metadata() {
        synchronized (this.monitor) {
            if (this.serviceMetadata == null) {
                this.serviceMetadata = new ServiceMetadata(this);
            }

            return this.serviceMetadata;
        }
    }

    Service requires(String... requires) {
        synchronized (this.monitor) {
            if (this.requires == null) {
                this.requires = new ArrayList<>();
            }
            Arrays.stream(requires).forEach(this.requires::add);
            return this;
        }
    }

    Plan plan() {
        synchronized (this.monitor) {
            if (this.plans == null) {
                this.plans = new ArrayList<>();
            }

            Plan plan = new Plan(this);
            this.plans.add(plan);
            return plan;
        }
    }

    DashboardClient dashboardClient() {
        synchronized (this.monitor) {
            this.dashboardClient = new DashboardClient(this);
            return this.dashboardClient;
        }
    }

}
