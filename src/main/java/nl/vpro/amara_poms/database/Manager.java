package nl.vpro.amara_poms.database;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import nl.vpro.amara_poms.database.task.Task;
import nl.vpro.amara_poms.database.task.TaskReader;
import nl.vpro.amara_poms.database.task.TaskWriter;

/**
 * Manager for csv file database
 */
public class Manager implements Iterable<Task> {

    private final static Logger LOG = LoggerFactory.getLogger(TaskReader.class);

    private String filenameTasks;
    private String filenameActivities;
    private final List<Task> tasks = new ArrayList<>();

    public void setFilenameTasks(String filenameTasks) {
        this.filenameTasks = filenameTasks;
    }
    public void setFilenameActivities(String filenameActivities) {
        this.filenameActivities = filenameActivities;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * singleton manager
     */
    private static final Manager INSTANCE = new Manager();

    public static Manager getInstance() {
        return INSTANCE;
    }

    /**
     * writing and reading
     */
    public void writeFile() {
        TaskWriter.writeCsvFile(filenameTasks, tasks);
    }
    public void readFile() {
        if (Files.exists((Paths.get(filenameTasks)))) {
            tasks.clear();
            tasks.addAll(TaskReader.readCsvFile(filenameTasks));
        } else {
            tasks.clear();
        }
    }

    /**
     * Remove all entries
     */
    public void clear() {
        tasks.clear();
    }

    /**
     * Add or update task
     */
    public void addOrUpdateTask(Task task) {

        // check if already exists
        Task foundTask = findTask(task.getVideoId(), task.getLanguage());
        if (foundTask == null) {
            task.setCreateDateTime(ZonedDateTime.now());
            task.setUpdateDateTime(ZonedDateTime.now());
        } else {
            // already found so merge
            task.setCreateDateTime(foundTask.getCreateDateTime());
            task.setUpdateDateTime(ZonedDateTime.now());
            removeTaskByVideoId(task.getVideoId(), task.getLanguage());
        }

        // add/update task
        tasks.add(task);
        writeFile();
    }

    /**
     * Find task by videoId and language
     */
    public Task findTask(String videoId, String language) {
        Task foundTask = null;

        List<Task> foundTasks = tasks.stream().filter((task) -> task.getVideoId().equals(videoId) &&
                                                                task.getLanguage().equals(language)).collect(Collectors.toList());

        if (foundTasks.size() == 0) {
            LOG.info(videoId + " not found (yet) in db");
        } else if (foundTasks.size() > 1) {
            LOG.error(videoId + " found more than 1 time in db");
            foundTask = foundTasks.get(0);
        } else {
            foundTask = foundTasks.get(0);
        }

        return  foundTask;
    }

    /**
     * Find task by source mid
     */
    public Task findTaskByPomsSourceId(String pomsMid) {
        Task foundTask = null;

        List<Task> foundTasks = tasks.stream().filter((task) -> task.getPomsSourceMid().equals(pomsMid)).collect(Collectors.toList());

        if (foundTasks.size() == 0) {
            LOG.info(pomsMid + " not found (yet) in db");
        } else if (foundTasks.size() > 1) {
            LOG.error(pomsMid + " found more than 1 time in db");
            foundTask = foundTasks.get(0);
        } else {
            foundTask = foundTasks.get(0);
        }

        return  foundTask;
    }

    /**
     * Remove task by videoId
     */
    public void removeTaskByVideoId(String videoId, String language) {
        LOG.info("Task with videoId " + videoId + " has been removed");
        tasks.removeIf((task) -> task.getVideoId().equals(videoId) && task.getLanguage() == language);
    }


    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
