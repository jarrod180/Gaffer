/*
 * Copyright 2016 Crown Copyright
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gaffer.operation.simple.hdfs;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import gaffer.operation.AbstractOperation;
import gaffer.operation.simple.hdfs.handler.jobfactory.JobInitialiser;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.ArrayList;
import java.util.List;


/**
 * The <code>MapReduceOperation</code> operation is the base operation that should be extended for any Operations that run map reduce jobs.
 * {@link JobInitialiser}.
 * <p>
 * For normal operation handlers the operation {@link gaffer.data.elementdefinition.view.View} will be ignored.
 * </p>
 * <b>NOTE</b> - currently this job has to be run as a hadoop job.
 *
 * @see MapReduceOperation.Builder
 */
public abstract class MapReduceOperation<INPUT, OUTPUT> extends AbstractOperation<INPUT, OUTPUT> {
    private List<Path> inputPaths = new ArrayList<>();
    private Path outputPath;
    private Path failurePath;
    private Integer numReduceTasks = null;
    private Integer numMapTasks = null;

    /**
     * A job initialiser that allows additional job initialisation to be carried out in addition to that done by the
     * store.
     * Most stores will probably require the Job Input to be configured in this initialiser as this is specific to the
     * type of data store in Hdfs.
     * For Avro data see {@link gaffer.operation.simple.hdfs.handler.jobfactory.AvroJobInitialiser}.
     * For Text data see {@link gaffer.operation.simple.hdfs.handler.jobfactory.TextJobInitialiser}.
     */
    private JobInitialiser jobInitialiser;
    private Class<? extends Partitioner> partitioner;

    public List<Path> getInputPaths() {
        return inputPaths;
    }

    public void setInputPaths(final List<Path> inputPaths) {
        this.inputPaths = inputPaths;
    }

    public void addInputPaths(final List<Path> inputPaths) {
        this.inputPaths.addAll(inputPaths);
    }

    public void addInputPath(final Path inputPath) {
        this.inputPaths.add(inputPath);
    }

    public Path getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(final Path outputPath) {
        this.outputPath = outputPath;
    }

    public Path getFailurePath() {
        return failurePath;
    }

    public void setFailurePath(final Path failurePath) {
        this.failurePath = failurePath;
    }

    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.WRAPPER_OBJECT, property = "class")
    public JobInitialiser getJobInitialiser() {
        return jobInitialiser;
    }

    public void setJobInitialiser(final JobInitialiser jobInitialiser) {
        this.jobInitialiser = jobInitialiser;
    }

    public Integer getNumMapTasks() {
        return numMapTasks;
    }

    public void setNumMapTasks(final Integer numMapTasks) {
        this.numMapTasks = numMapTasks;
    }

    public Integer getNumReduceTasks() {
        return numReduceTasks;
    }

    public void setNumReduceTasks(final Integer numReduceTasks) {
        this.numReduceTasks = numReduceTasks;
    }

    public Class<? extends Partitioner> getPartitioner() {
        return partitioner;
    }

    public void setPartitioner(final Class<? extends Partitioner> partitioner) {
        this.partitioner = partitioner;
    }

    public static class Builder<OP_TYPE extends MapReduceOperation<INPUT, OUTPUT>, INPUT, OUTPUT> extends AbstractOperation.Builder<OP_TYPE, INPUT, OUTPUT> {

        public Builder(final OP_TYPE op) {
            super(op);
        }

        public Builder setInputPaths(final List<Path> inputPaths) {
            op.setInputPaths(inputPaths);
            return this;
        }

        public Builder addInputPaths(final List<Path> inputPaths) {
            op.addInputPaths(inputPaths);
            return this;
        }

        public Builder addInputPath(final Path inputPath) {
            op.addInputPath(inputPath);
            return this;
        }

        public Builder outputPath(final Path outputPath) {
            op.setOutputPath(outputPath);
            return this;
        }

        public Builder failurePath(final Path failurePath) {
            op.setFailurePath(failurePath);
            return this;
        }

        public Builder jobInitialiser(final JobInitialiser jobInitialiser) {
            op.setJobInitialiser(jobInitialiser);
            return this;
        }

        public Builder reducers(final Integer numReduceTasks) {
            op.setNumReduceTasks(numReduceTasks);
            return this;
        }

        public Builder mappers(final Integer numMapTasks) {
            op.setNumMapTasks(numMapTasks);
            return this;
        }

        public Builder partioner(final Class<? extends Partitioner> partitioner) {
            op.setPartitioner(partitioner);
            return this;
        }

        @Override
        public Builder option(final String name, final String value) {
            super.option(name, value);
            return this;
        }
    }
}