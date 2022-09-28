# Cqrs- Event Sourcing

I used Axon framework for event sourcing.

#### Axon terms

* AggreagateLifeCycle: use for publish event.
  While executing a command, it notifies the rest of the application
that a new event has been created.

* CommandGateway: It is Axon's interface for command processing components. 
The results of commands sent with the command gateway can be expected. 

* TargetAggregateIdentifier-AggregateIdentifier: This is identifier for 
command and aggregate

#### Command Flow

- comes request by model to controller
- incmoming models converts to comand and CommandGateway process this command
- command has executed by aggregate, well event has created

#### While adding new flow

- Create a equal model to incoming request
- Create command or query, specify the target with same parameter
- Create an event for publish via aggregate
- Create an aggregate, dont forget to specify aggregate annotation
- publish an event by AggregateLifeCycle in a method 
(method that i mentioned must be constructor for create events)
- create a method and specify that @EventSourcingHandler to catch event
