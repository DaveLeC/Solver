package rocks.lecomte.solver.service;


import com.amazonaws.services.ecs.AmazonECS;
import com.amazonaws.services.ecs.AmazonECSClientBuilder;
import com.amazonaws.services.ecs.model.RunTaskRequest;

import lombok.NoArgsConstructor;
import rocks.lecomte.solver.SolverRequest;

@NoArgsConstructor
public class SolverService implements ExecutorService<SolverRequest, String> {

    public String process(SolverRequest request) {
        AmazonECS client = AmazonECSClientBuilder.standard().build();
        RunTaskRequest taskRequest = new RunTaskRequest()
                .withCluster("SolverECSCluster")
                .withTaskDefinition("first-run-task-definition");

        return client.runTask(taskRequest).toString();


    }
}
