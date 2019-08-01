package rocks.lecomte.solver.service;


import com.amazonaws.services.ecs.AmazonECS;
import com.amazonaws.services.ecs.AmazonECSClientBuilder;
import com.amazonaws.services.ecs.model.*;

import lombok.NoArgsConstructor;
import rocks.lecomte.solver.SolverRequest;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
public class SolverService implements ExecutorService<SolverRequest, String> {
    /*
     client = boto3.client('ecs')
        print("Starting task")
        response = client.run_task(
            cluster=os.getenv('CLUSTER'),
            launchType=os.getenv('LAUNCH_TYPE', 'FARGATE'),
            taskDefinition=os.getenv('TASK_DEFINITION'),
            overrides={
                'containerOverrides': [{
                    'name':'Solver',
                    'environment':[{'name':'SQS_ID','value':context.aws_request_id}]
                }],
                'taskRoleArn':'arn:aws:iam::029103258696:role/ecsTaskExecutionRole'
            },
            count=int(os.getenv('COUNT', 1)),
            platformVersion='LATEST',
            networkConfiguration={
                'awsvpcConfiguration': {
                    'subnets': os.getenv('SUBNETS').split(','),
                     'assignPublicIp': os.getenv('ASSIGN_PUBLIC_IP', 'ENABLED'),
                     'securityGroups': os.getenv('SECURITY_GROUPS').split(','),
                },
            }
        )
     */
    public String process(SolverRequest request) {
        ServiceFactory.getPersistenceService().save(request);

        AmazonECS client = AmazonECSClientBuilder.standard().build();

        String securityGroup = "sg-0ab9bb80655a46d95";
        Collection<String> subnets = new LinkedList<>();
        subnets.add("subnet-c5369e9f");

        String assignPublicIp = "ENABLED";
        TaskOverride overrides = new TaskOverride();
        overrides.setTaskRoleArn("arn:aws:iam::029103258696:role/ecsTaskExecutionRole");
        ContainerOverride override = new ContainerOverride();
        KeyValuePair kp = new KeyValuePair();
        kp.setName("ID");
        kp.setValue(request.getIdentifier());
        override.getEnvironment().add(kp);
        List<ContainerOverride> co = overrides.getContainerOverrides();
        co.add(override);
        override.setName("Solver");

        NetworkConfiguration network = new NetworkConfiguration();
        AwsVpcConfiguration vpc = new AwsVpcConfiguration();
        vpc.setAssignPublicIp(assignPublicIp);
        network.setAwsvpcConfiguration(vpc);
        network.getAwsvpcConfiguration().setSubnets(subnets);

        RunTaskRequest taskRequest = new
                RunTaskRequest()
                .withLaunchType(LaunchType.FARGATE)
                .withCluster("SolverECSCluster")
                .withOverrides(overrides)
                .withNetworkConfiguration(network)
                .withTaskDefinition("first-run-task-definition:2");
        taskRequest.withCount(1);
        taskRequest.setPlatformVersion("LATEST");

        return client.runTask(taskRequest).getTasks().get(0).getTaskArn();
    }
}
