
# Since the main iksemel package contains only a single library, it will become
# subject to renaming by debian.bbclass. Since meta-rdk contains package groups
# etc which reference "iksemel" directly (rather than the package name after
# renaming) prevent renaming as a temp solution to allow the package groups to
# continue to depend on "iksemel".

AUTO_LIBNAME_PKGS = ""
