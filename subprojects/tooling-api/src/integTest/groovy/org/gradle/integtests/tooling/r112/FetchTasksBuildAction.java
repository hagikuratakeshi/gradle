/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.integtests.tooling.r112;

import org.gradle.tooling.BuildAction;
import org.gradle.tooling.BuildController;
import org.gradle.tooling.model.GradleTask;
import org.gradle.tooling.model.gradle.BasicGradleProject;
import org.gradle.tooling.model.gradle.BuildInvocations;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by radim on 3/15/14.
 */
public class FetchTasksBuildAction implements BuildAction<List<GradleTask>> {
    private final String projectPath;

    FetchTasksBuildAction(String projectPath) {
        this.projectPath = projectPath;
    }

    public List<GradleTask> execute(BuildController controller) {
        BasicGradleProject project = null;
        for (BasicGradleProject p : controller.getBuildModel().getProjects()) {
            if (p.getPath().equals(projectPath)) {
                project = p;
                break;
            }
        }

        List<GradleTask> result = new ArrayList<GradleTask>();
        for (GradleTask task : controller.getModel(project, BuildInvocations.class).getTasks()) {
            result.add(task);
        }
        return result;
    }
}
