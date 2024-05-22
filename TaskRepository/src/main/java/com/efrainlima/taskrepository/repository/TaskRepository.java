package com.efrainlima.taskrepository.repository;

import com.efrainlima.taskrepository.data.Task;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TaskRepository {

    @Select("SELECT * FROM Tasks")
    List<Task> findAll();

    @Select("SELECT * FROM Tasks WHERE id = #{id}")
    Task findById(String id);

    @Insert("INSERT INTO Tasks(id, taskName, taskDescription, initDate, isClosed) VALUES(#{id}, #{taskName},\n" +
            "        #{taskDescription}, #{initDate}, #{isClosed})")
    void save(Task task);

    @Update("UPDATE Tasks SET taskName = #{taskName}, taskDescription = #{taskDescription}, initDate = #{initDate},\n" +
            "        isClosed = #{isClosed} WHERE id = #{id}")
    void update(Task task);

    @Update("UPDATE Tasks SET isClosed = 'true' WHERE id = #{id}")
    void updateClose(String id);

    @Delete("DELETE FROM Tasks WHERE id = #{id}")
    void delete(String id);
}
