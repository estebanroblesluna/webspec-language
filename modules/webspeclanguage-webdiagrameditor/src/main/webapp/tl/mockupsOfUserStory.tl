        <a href="userStories.htm?sprintId={sprintId}">&larr; Back to user stories</a><br><br>
        <button id="add" type="submit" class="btn btn-primary btn-mini" userStoryId="{userStoryId}">Add new</button>
        <h3>Mockups for user story</h3>
        <table class="table table-condensed">
        <thead>
          <tr>
            <th>Name</th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {#mockups}
          <tr>
            <td>{title}</td>
            <td><button type="submit" class="btn btn-mini modifyMockup" mockupId="{id}" userStoryId="{userStoryId}">Modify</button></td>
          </tr>
          {/mockups}
        </tbody>
        </table>
        