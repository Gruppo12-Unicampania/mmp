-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Gen 21, 2021 alle 12:20
-- Versione del server: 10.4.14-MariaDB
-- Versione PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mydbs`
--

-- --------------------------------------------------------

--
-- Struttura della tabella `album`
--

CREATE TABLE `album` (
  `album_name` varchar(30) NOT NULL,
  `id_etichetta` bigint(20) NOT NULL,
  `album_image` varchar(300) NOT NULL DEFAULT 'image/default.jpg'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura stand-in per le viste `approved_midi`
-- (Vedi sotto per la vista effettiva)
--
CREATE TABLE `approved_midi` (
`midi_path` varchar(300)
,`id_song` bigint(20)
,`album_name` varchar(30)
,`Title` varchar(30)
,`Composer` varchar(30)
,`Singer` varchar(45)
);

-- --------------------------------------------------------

--
-- Struttura stand-in per le viste `approved_scores`
-- (Vedi sotto per la vista effettiva)
--
CREATE TABLE `approved_scores` (
`path` varchar(300)
,`id_song` bigint(20)
,`album_name` varchar(30)
,`Title` varchar(30)
,`Composer` varchar(30)
,`Singer` varchar(45)
);

-- --------------------------------------------------------

--
-- Struttura della tabella `midi`
--

CREATE TABLE `midi` (
  `id_song` bigint(20) NOT NULL,
  `id_user` bigint(20) NOT NULL,
  `midi_path` varchar(300) NOT NULL,
  `approved` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura stand-in per le viste `midi_view2`
-- (Vedi sotto per la vista effettiva)
--
CREATE TABLE `midi_view2` (
`midi_path` varchar(300)
,`id_song` bigint(20)
,`Title` varchar(30)
,`id_etichetta` bigint(20)
,`album_name` varchar(30)
);

-- --------------------------------------------------------

--
-- Struttura della tabella `musical_scores`
--

CREATE TABLE `musical_scores` (
  `id_user` bigint(20) NOT NULL,
  `id_song` bigint(20) NOT NULL,
  `path` varchar(300) NOT NULL,
  `approved` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura stand-in per le viste `musical_scores_approve`
-- (Vedi sotto per la vista effettiva)
--
CREATE TABLE `musical_scores_approve` (
`path` varchar(300)
,`id_song` bigint(20)
,`album_name` varchar(30)
,`Title` varchar(30)
);

-- --------------------------------------------------------

--
-- Struttura stand-in per le viste `musical_scores_view2`
-- (Vedi sotto per la vista effettiva)
--
CREATE TABLE `musical_scores_view2` (
`path` varchar(300)
,`id_song` bigint(20)
,`id_etichetta` bigint(20)
,`album_name` varchar(30)
,`Title` varchar(30)
);

-- --------------------------------------------------------

--
-- Struttura della tabella `playlist_container`
--

CREATE TABLE `playlist_container` (
  `idplaylist` bigint(20) NOT NULL,
  `id_user` bigint(20) DEFAULT NULL,
  `pl_image` varchar(200) DEFAULT NULL,
  `pl_name` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `songs`
--

CREATE TABLE `songs` (
  `id_song` bigint(20) NOT NULL,
  `song_path` varchar(300) NOT NULL,
  `Title` varchar(30) NOT NULL DEFAULT 'unknown',
  `Composer` varchar(30) NOT NULL DEFAULT 'unknown',
  `Singer` varchar(45) NOT NULL DEFAULT 'unknown',
  `Type` varchar(3) NOT NULL,
  `album_name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura della tabella `song_container`
--

CREATE TABLE `song_container` (
  `id_user` bigint(20) NOT NULL,
  `id_playlist` bigint(20) NOT NULL,
  `id_song` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Struttura stand-in per le viste `song_midi_approve`
-- (Vedi sotto per la vista effettiva)
--
CREATE TABLE `song_midi_approve` (
`midi_path` varchar(300)
,`id_song` bigint(20)
,`album_name` varchar(30)
,`Title` varchar(30)
);

-- --------------------------------------------------------

--
-- Struttura della tabella `users`
--

CREATE TABLE `users` (
  `ID` bigint(20) NOT NULL,
  `Username` varchar(30) NOT NULL,
  `Password` varchar(30) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `admin` tinyint(1) NOT NULL DEFAULT 0,
  `vip` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `users`
--

INSERT INTO `users` (`ID`, `Username`, `Password`, `Email`, `admin`, `vip`) VALUES
(1, 'amministratore', 'amministratore', 'admin@admin.it', 1, 1),
(2, 'etichetta', 'etichetta', 'etichetta@etichetta.it', 1, 1),
(3, 'utentevip', 'utentevip', 'vip@localhost.it', 0, 1),
(4, 'utente', 'utente', 'utente@localhost.here', 0, 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `vip_request`
--

CREATE TABLE `vip_request` (
  `id_user` bigint(20) NOT NULL,
  `vip_request` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struttura stand-in per le viste `vista_pl`
-- (Vedi sotto per la vista effettiva)
--
CREATE TABLE `vista_pl` (
`id_song` bigint(20)
,`type` varchar(3)
,`link_url` varchar(300)
,`cover` varchar(300)
,`name` varchar(30)
,`id_playlist` bigint(20)
,`album` varchar(30)
,`composer` varchar(30)
,`singer` varchar(45)
);

-- --------------------------------------------------------

--
-- Struttura per vista `approved_midi`
--
DROP TABLE IF EXISTS `approved_midi`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `approved_midi`  AS SELECT `midi`.`midi_path` AS `midi_path`, `midi`.`id_song` AS `id_song`, `songs`.`album_name` AS `album_name`, `songs`.`Title` AS `Title`, `songs`.`Composer` AS `Composer`, `songs`.`Singer` AS `Singer` FROM (`midi` join `songs`) WHERE `songs`.`id_song` = `midi`.`id_song` AND `midi`.`approved` = 1 ;

-- --------------------------------------------------------

--
-- Struttura per vista `approved_scores`
--
DROP TABLE IF EXISTS `approved_scores`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `approved_scores`  AS SELECT `musical_scores`.`path` AS `path`, `musical_scores`.`id_song` AS `id_song`, `songs`.`album_name` AS `album_name`, `songs`.`Title` AS `Title`, `songs`.`Composer` AS `Composer`, `songs`.`Singer` AS `Singer` FROM (`musical_scores` join `songs`) WHERE `songs`.`id_song` = `musical_scores`.`id_song` AND `musical_scores`.`approved` = 1 ;

-- --------------------------------------------------------

--
-- Struttura per vista `midi_view2`
--
DROP TABLE IF EXISTS `midi_view2`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `midi_view2`  AS SELECT `song_midi_approve`.`midi_path` AS `midi_path`, `song_midi_approve`.`id_song` AS `id_song`, `song_midi_approve`.`Title` AS `Title`, `album`.`id_etichetta` AS `id_etichetta`, `album`.`album_name` AS `album_name` FROM (`song_midi_approve` join `album`) WHERE `album`.`album_name` = `song_midi_approve`.`album_name` ;

-- --------------------------------------------------------

--
-- Struttura per vista `musical_scores_approve`
--
DROP TABLE IF EXISTS `musical_scores_approve`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `musical_scores_approve`  AS SELECT `musical_scores`.`path` AS `path`, `musical_scores`.`id_song` AS `id_song`, `songs`.`album_name` AS `album_name`, `songs`.`Title` AS `Title` FROM (`musical_scores` join `songs`) WHERE `songs`.`id_song` = `musical_scores`.`id_song` AND `musical_scores`.`approved` = 0 ;

-- --------------------------------------------------------

--
-- Struttura per vista `musical_scores_view2`
--
DROP TABLE IF EXISTS `musical_scores_view2`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `musical_scores_view2`  AS SELECT `musical_scores_approve`.`path` AS `path`, `musical_scores_approve`.`id_song` AS `id_song`, `album`.`id_etichetta` AS `id_etichetta`, `album`.`album_name` AS `album_name`, `musical_scores_approve`.`Title` AS `Title` FROM (`musical_scores_approve` join `album`) WHERE `album`.`album_name` = `musical_scores_approve`.`album_name` ;

-- --------------------------------------------------------

--
-- Struttura per vista `song_midi_approve`
--
DROP TABLE IF EXISTS `song_midi_approve`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `song_midi_approve`  AS SELECT `midi`.`midi_path` AS `midi_path`, `midi`.`id_song` AS `id_song`, `songs`.`album_name` AS `album_name`, `songs`.`Title` AS `Title` FROM (`midi` join `songs`) WHERE `songs`.`id_song` = `midi`.`id_song` AND `midi`.`approved` = 0 ;

-- --------------------------------------------------------

--
-- Struttura per vista `vista_pl`
--
DROP TABLE IF EXISTS `vista_pl`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_pl`  AS SELECT `songs`.`id_song` AS `id_song`, `songs`.`Type` AS `type`, `songs`.`song_path` AS `link_url`, `album`.`album_image` AS `cover`, `songs`.`Title` AS `name`, `song_container`.`id_playlist` AS `id_playlist`, `songs`.`album_name` AS `album`, `songs`.`Composer` AS `composer`, `songs`.`Singer` AS `singer` FROM ((`song_container` join `songs`) join `album`) WHERE `songs`.`id_song` = `song_container`.`id_song` AND `songs`.`album_name` = `album`.`album_name` ;

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `album`
--
ALTER TABLE `album`
  ADD PRIMARY KEY (`album_name`),
  ADD KEY `id_etichetta` (`id_etichetta`);

--
-- Indici per le tabelle `midi`
--
ALTER TABLE `midi`
  ADD KEY `id_userr` (`id_user`),
  ADD KEY `id_song` (`id_song`);

--
-- Indici per le tabelle `musical_scores`
--
ALTER TABLE `musical_scores`
  ADD KEY `id_songgg` (`id_song`),
  ADD KEY `id_userrrr` (`id_user`);

--
-- Indici per le tabelle `playlist_container`
--
ALTER TABLE `playlist_container`
  ADD PRIMARY KEY (`idplaylist`),
  ADD UNIQUE KEY `idplaylist_UNIQUE` (`idplaylist`),
  ADD KEY `id_user_idx` (`id_user`);

--
-- Indici per le tabelle `songs`
--
ALTER TABLE `songs`
  ADD PRIMARY KEY (`id_song`),
  ADD UNIQUE KEY `id_song_UNIQUE` (`id_song`),
  ADD UNIQUE KEY `song_path_UNIQUE` (`song_path`),
  ADD KEY `album name_idx` (`album_name`);

--
-- Indici per le tabelle `song_container`
--
ALTER TABLE `song_container`
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_playlist` (`id_playlist`),
  ADD KEY `id_song` (`id_song`);

--
-- Indici per le tabelle `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Username` (`Username`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- Indici per le tabelle `vip_request`
--
ALTER TABLE `vip_request`
  ADD UNIQUE KEY `id_user` (`id_user`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `playlist_container`
--
ALTER TABLE `playlist_container`
  MODIFY `idplaylist` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `songs`
--
ALTER TABLE `songs`
  MODIFY `id_song` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT per la tabella `users`
--
ALTER TABLE `users`
  MODIFY `ID` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `album`
--
ALTER TABLE `album`
  ADD CONSTRAINT `album_ibfk_1` FOREIGN KEY (`id_etichetta`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `midi`
--
ALTER TABLE `midi`
  ADD CONSTRAINT `id_song` FOREIGN KEY (`id_song`) REFERENCES `songs` (`id_song`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `id_userr` FOREIGN KEY (`id_user`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `musical_scores`
--
ALTER TABLE `musical_scores`
  ADD CONSTRAINT `id_songgg` FOREIGN KEY (`id_song`) REFERENCES `songs` (`id_song`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `id_userrrr` FOREIGN KEY (`id_user`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `playlist_container`
--
ALTER TABLE `playlist_container`
  ADD CONSTRAINT `id_user` FOREIGN KEY (`id_user`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `songs`
--
ALTER TABLE `songs`
  ADD CONSTRAINT `album name` FOREIGN KEY (`album_name`) REFERENCES `album` (`album_name`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Limiti per la tabella `song_container`
--
ALTER TABLE `song_container`
  ADD CONSTRAINT `song_container_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `song_container_ibfk_2` FOREIGN KEY (`id_playlist`) REFERENCES `playlist_container` (`idplaylist`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `song_container_ibfk_3` FOREIGN KEY (`id_song`) REFERENCES `songs` (`id_song`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Limiti per la tabella `vip_request`
--
ALTER TABLE `vip_request`
  ADD CONSTRAINT `id_userrrrrrrrrrr` FOREIGN KEY (`id_user`) REFERENCES `users` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
