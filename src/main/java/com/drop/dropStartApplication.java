package com.drop;

import com.drop.health.TemplateHealthCheck;
import com.drop.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class dropStartApplication extends Application<dropStartConfiguration> {

    public static void main(final String[] args) throws Exception {
        new dropStartApplication().run(args);
    }

    @Override
    public String getName() {
        return "dropStart";
    }

    @Override
    public void initialize(final Bootstrap<dropStartConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(dropStartConfiguration configuration,
                    Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        environment.jersey().register(resource);
    }

}
