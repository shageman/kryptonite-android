package co.krypt.kryptonite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import co.krypt.kryptonite.knownhosts.KnownHost;
import co.krypt.kryptonite.log.GitCommitSignatureLog;
import co.krypt.kryptonite.log.GitTagSignatureLog;
import co.krypt.kryptonite.log.SSHSignatureLog;


/**
 * Created by Kevin King on 3/28/17.
 * Copyright 2016. KryptCo, Inc.
 */

public class OpenDatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "kryptonite";
    private static final int DATABASE_VERSION = 3;

    /**
     * The data access object used to interact with the Sqlite database to do C.R.U.D operations.
     */
    private Dao<SSHSignatureLog, Long> sshSignatureLogDao;
    private Dao<GitCommitSignatureLog, Long> gitCommitSignatureLogDao;
    private Dao<GitTagSignatureLog, Long> gitTagSignatureLogDao;

    private Dao<KnownHost, Long> knownHostDao;

    public OpenDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION) ;
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, SSHSignatureLog.class);
            TableUtils.createTable(connectionSource, KnownHost.class);
            TableUtils.createTable(connectionSource, GitCommitSignatureLog.class);
            TableUtils.createTable(connectionSource, GitTagSignatureLog.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
        try {
            /**
             * Recreates the database when onUpgrade is called by the framework
             */
            if (oldVersion < 2 && newVersion >= 2) {
                TableUtils.createTable(connectionSource, KnownHost.class);
            }
            if (oldVersion < 3 && newVersion >= 3) {
                TableUtils.createTable(connectionSource, GitCommitSignatureLog.class);
                TableUtils.createTable(connectionSource, GitTagSignatureLog.class);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns an instance of the data access object
     * @return
     * @throws SQLException
     */
    public Dao<SSHSignatureLog, Long> getSSHSignatureLogDao() throws SQLException {
        if(sshSignatureLogDao == null) {
            sshSignatureLogDao = getDao(SSHSignatureLog.class);
        }
        return sshSignatureLogDao;
    }

    public Dao<GitCommitSignatureLog, Long> getGitCommitSignatureLogDao() throws SQLException {
        if(gitCommitSignatureLogDao == null) {
            gitCommitSignatureLogDao = getDao(GitCommitSignatureLog.class);
        }
        return gitCommitSignatureLogDao;
    }

    public Dao<GitTagSignatureLog, Long> getGitTagSignatureLogDao() throws SQLException {
        if(gitTagSignatureLogDao == null) {
            gitTagSignatureLogDao = getDao(GitTagSignatureLog.class);
        }
        return gitTagSignatureLogDao;
    }

    public Dao<KnownHost, Long> getKnownHostDao() throws SQLException {
        if(knownHostDao == null) {
            knownHostDao = getDao(KnownHost.class);
        }
        return knownHostDao;
    }
}
